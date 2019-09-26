package com.lernopus.lernopus.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lernopus.lernopus.exception.BadRequestException;
import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaLearnAttachments;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.repository.LaCourseRepository;
import com.lernopus.lernopus.repository.LaUserRepository;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.util.AppConstants;
import com.lernopus.lernopus.util.ModelMapper;

@Service
public class LaCourseService {

    @Autowired
    private LaCourseRepository laCourseRepository;

    @Autowired
    private LaUserRepository userRepository;

    public PagedResponse<LaCourseResponse> getAllCourses(LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.findAll(pageable);

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }

    public PagedResponse<LaCourseResponse> getCoursesCreatedBy(String username, LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        LaLearnUser user = userRepository.findByLaUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all courses created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.findByLaCreatedUser(user.getLaUserId(), pageable);

        if (courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    user);
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }

    public LaLearnCourse createCourse(LaCourseRequest courseRequest) {
        LaLearnCourse course = new LaLearnCourse();
        course.setLaCourseName(courseRequest.getLaCourseName());
        course.setLaCourseContentHtml(courseRequest.getLaCourseContentHtml());
        course.setLaCourseContentText(courseRequest.getLaCourseContentText());
        course.setLaIsNote(courseRequest.getLaIsNote());
        course.setLaAuthorId(courseRequest.getLaAuthorId());
        course.setLaTechTag(courseRequest.getLaTechTag());
        courseRequest.getLaLearnAttachments().forEach(attachRequest -> {
        	LaLearnAttachments laLearnAttachments;
			try {
				laLearnAttachments = new LaLearnAttachments(attachRequest.getLaAttachmentPath(), attachRequest.getLaAttachName(), attachRequest.getLaAttachmentType(), attachRequest.getLaAttachmentSize(), attachRequest.getLaAttachExtension(), attachRequest.getLaAttachFileId(), attachRequest.getLaAttachPreview(), attachRequest.getLaAttachSizeReadable(), attachRequest.getLaAttachBlob());
				course.addLaLearnAttachment(laLearnAttachments);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });

        return laCourseRepository.save(course);
    }

    public LaCourseResponse getCourseById(Long courseId, LaUserPrincipal currentUser) {
        LaLearnCourse course = laCourseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));

        // Retrieve course creator details
        LaLearnUser creator = userRepository.findById(course.getLaCreatedUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", course.getLaCreatedUser()));

        return ModelMapper.mapCourseToCourseResponse(course, 
                creator);
    }


    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    Map<Long, LaLearnUser> getCourseCreatorMap(List<LaLearnCourse> course) {
        // Get Course Creator details of the given list of courses
        List<Long> creatorIds = course.stream()
                .map(LaLearnCourse::getLaCreatedUser)
                .distinct()
                .collect(Collectors.toList());

        List<LaLearnUser> creators = userRepository.findByLaUserIdIn(creatorIds);
        Map<Long, LaLearnUser> creatorMap = creators.stream()
                .collect(Collectors.toMap(LaLearnUser::getLaUserId, Function.identity()));

        return creatorMap;
    }
}
