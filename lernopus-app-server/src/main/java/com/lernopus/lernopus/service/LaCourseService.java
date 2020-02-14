package com.lernopus.lernopus.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lernopus.lernopus.exception.BadRequestException;
import com.lernopus.lernopus.exception.FileStorageException;
import com.lernopus.lernopus.exception.MyFileNotFoundException;
import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaCourseAttachFile;
import com.lernopus.lernopus.model.LaLearnAttachments;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.repository.LaAttachRepository;
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
    
    @Autowired
    private LaAttachRepository laAttachRepository;

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
    
    public PagedResponse<LaCourseResponse> getAllRootCourses(LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getAllRootCourse(pageable);

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
				laLearnAttachments = new LaLearnAttachments(attachRequest.getLaAttachName(), attachRequest.getLaAttachExtension(), attachRequest.getLaAttachFileId(), attachRequest.getLaAttachPreview(), attachRequest.getLaAttachSizeReadable(), attachRequest.getLaAttachFileRefId());
				course.addLaLearnAttachment(laLearnAttachments);
			} catch (JsonProcessingException e) {
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
    
    public LaCourseResponse getCourseById(Long courseId) {
        LaLearnCourse course = laCourseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));

        return ModelMapper.mapCourseToCourseResponse(course, 
                null);
    }
    
    
    public LaLearnCourse updateParentAndRootId(LaLearnCourse course, Long laCourseParentId, Long laCourseRootId) {
    	course.setLaCourseParentId(laCourseParentId);
    	course.setLaCourseRootId(laCourseRootId);
        return laCourseRepository.save(course);
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
    
    public LaCourseAttachFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String checksum = "";
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            MessageDigest md5Digest;
			try {
				md5Digest = MessageDigest.getInstance("MD5");
				File tempFile = File.createTempFile(fileName, null, null);
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(file.getBytes());
				fos.close();
				checksum = getFileChecksum(md5Digest, tempFile);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			Optional<LaCourseAttachFile> existingFile = laAttachRepository.findById(checksum);
			if(existingFile.isPresent())
			{
				return laAttachRepository.findById(checksum)
		                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileName));
			}
			else
			{
				LaCourseAttachFile dbFile = new LaCourseAttachFile(checksum, fileName, file.getContentType(), file.getBytes());
				return laAttachRepository.save(dbFile);
			}
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public LaCourseAttachFile getFile(String fileId) {
        return laAttachRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);
         
        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0; 
          
        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };
         
        //close the stream; We don't need it now.
        fis.close();
         
        //Get the hash's bytes
        byte[] bytes = digest.digest();
         
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        //return complete hash
       return sb.toString();
    }

    public PagedResponse<LaCourseResponse> getAllChildCourses(Long courseId, LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getAllChildCourse(courseId, pageable);

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
}
