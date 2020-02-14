package com.lernopus.lernopus.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lernopus.lernopus.model.LaCourseAttachFile;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaCourseUploadFileResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.security.CurrentUser;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.service.LaCourseService;
import com.lernopus.lernopus.util.AppConstants;

/**
 * Created by amernath v on 2019-09-04.
 */

@RestController
@RequestMapping("/api/courses")
public class LaCourseController {

    @Autowired
    private LaCourseService courseService;

    @GetMapping
    public PagedResponse<LaCourseResponse> getCourses(@CurrentUser LaUserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return courseService.getAllRootCourses(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody LaCourseRequest courseRequest) {
    	LaLearnCourse course = courseService.createCourse(courseRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getLaCourseId()).toUri();
        if(Objects.nonNull(courseRequest.getLaCourseParentId()))
        {
        	LaCourseResponse laCourseResponse = courseService.getCourseById(courseRequest.getLaCourseParentId());
        	courseService.updateParentAndRootId(course, courseRequest.getLaCourseParentId() , laCourseResponse.getCourseRootId());
        }
        else
        {
        	courseService.updateParentAndRootId(course, course.getLaCourseId() , course.getLaCourseId());
        }
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "Course Created Successfully"));
    }


    @GetMapping("/{courseId}")
    public LaCourseResponse getCourseById(@CurrentUser LaUserPrincipal currentUser,
                                    @PathVariable Long courseId,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    	LaCourseResponse courseById = courseService.getCourseById(courseId, currentUser);
    	PagedResponse<LaCourseResponse> childCoursePageResponse = courseService.getAllChildCourses(Long.valueOf(courseId), currentUser,page,size);
    	courseById.setChildCoursePageResponse(childCoursePageResponse);
        return courseById;
    }

    @GetMapping("/learnCourseId/{learnCourseId}")
    @PreAuthorize("hasRole('USER')")
    public LaCourseResponse getLaCourseDetails(@PathVariable(value = "learnCourseId") String laCourseId,
                                                         @CurrentUser LaUserPrincipal currentUser,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    	LaCourseResponse courseById = courseService.getCourseById(Long.valueOf(laCourseId), currentUser);
    	PagedResponse<LaCourseResponse> childCoursePageResponse = courseService.getAllChildCourses(Long.valueOf(laCourseId), currentUser,page,size);
    	courseById.setChildCoursePageResponse(childCoursePageResponse);
        return courseById;
    }
    
    @PostMapping("/uploadFile")
    public LaCourseUploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        LaCourseAttachFile dbFile = courseService.storeFile(file);

        String fileDownloadUri = dbFile.getLaCourseAttachId();

        return new LaCourseUploadFileResponse(fileDownloadUri);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<LaCourseUploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
    	LaCourseAttachFile dbFile = courseService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getLaAttachType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getLaAttachName() + "\"")
                .body(new ByteArrayResource(dbFile.getLaCourseAttachData()));
    }


}
