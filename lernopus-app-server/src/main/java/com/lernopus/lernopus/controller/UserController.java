package com.lernopus.lernopus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaUserSummary;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.payload.UserIdentityAvailability;
import com.lernopus.lernopus.payload.UserProfile;
import com.lernopus.lernopus.repository.LaCourseRepository;
import com.lernopus.lernopus.repository.LaUserRepository;
import com.lernopus.lernopus.security.CurrentUser;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.service.LaCourseService;
import com.lernopus.lernopus.util.AppConstants;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private LaUserRepository userRepository;

    @Autowired
    private LaCourseRepository laCourseRepository;

    @Autowired
    private LaCourseService courseService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public LaUserSummary getCurrentUser(@CurrentUser LaUserPrincipal currentUser) {
        LaUserSummary userSummary = new LaUserSummary(currentUser.getLaUserId(), currentUser.getUsername(), currentUser.getLaUserFullName(), currentUser.getLaImagePath());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "la_user_name") String laUserName) {
        Boolean isAvailable = !userRepository.existsByLaUserName(laUserName);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "la_mail_id") String laMailId) {
        Boolean isAvailable = !userRepository.existsByLaMailId(laMailId);
        return new UserIdentityAvailability(isAvailable);
    }
    
    @GetMapping("/user/checkPhoneNumberAvailability")
    public UserIdentityAvailability checkPhoneNumberAvailability(@RequestParam(value = "la_phone_number") Long laPhoneNumber) {
        Boolean isAvailable = !userRepository.existsByLaPhoneNumber(laPhoneNumber);
        return new UserIdentityAvailability(isAvailable);
    }


    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        LaLearnUser user = userRepository.findByLaUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long courseCount = laCourseRepository.countByLaCreatedUser(user.getLaUserId());
        long voteCount = 0;

        UserProfile userProfile = new UserProfile(user.getLaUserId(), user.getLaUserName(), user.getLaUserFullName(), user.getLaCreatedAt(), courseCount, voteCount, user.getLaImagePath());

        return userProfile;
    }

    @GetMapping("/users/{username}/courses")
    public PagedResponse<LaCourseResponse> getCoursesCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser LaUserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return courseService.getCoursesCreatedBy(username, currentUser, page, size);
    }
    
}
