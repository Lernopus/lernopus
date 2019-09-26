package com.lernopus.lernopus.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
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
        return courseService.getAllCourses(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody LaCourseRequest courseRequest) {
    	LaLearnCourse course = courseService.createCourse(courseRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getLaCourseId()).toUri();

        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "Course Created Successfully"));
    }


    @GetMapping("/{courseId}")
    public LaCourseResponse getCourseById(@CurrentUser LaUserPrincipal currentUser,
                                    @PathVariable Long courseId) {
        return courseService.getCourseById(courseId, currentUser);
    }

    @GetMapping("/learnCourseId/{learnCourseId}")
    @PreAuthorize("hasRole('USER')")
    public LaCourseResponse getLaCourseDetails(@PathVariable(value = "learnCourseId") String laCourseId,
                                                         @CurrentUser LaUserPrincipal currentUser) {
        return courseService.getCourseById(Long.valueOf(laCourseId), currentUser);
    }

}
