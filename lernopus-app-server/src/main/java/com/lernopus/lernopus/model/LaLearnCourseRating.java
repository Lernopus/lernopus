package com.lernopus.lernopus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_course_rating")
public class LaLearnCourseRating {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_rating_id")
    @Size(max = 20)
    private Long laRatingId;

	@Column(name = "la_upvote_count")
    private Long laUpvoteCount;
    
    @Column(name = "la_downvote_count")
    private Long laDownvoteCount;
    
    @Column(name = "la_user_rating")
    private Long laUserRating;
    
    @Column(name = "la_admin_rating")
    private Long laAdminRating;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnCourse laLearnCourse;
    
    public LaLearnCourseRating() {

    }

    public LaLearnCourseRating(Long laUpvoteCount, Long laDownvoteCount, Long laUserRating, Long laAdminRating) {
        this.laUpvoteCount = laUpvoteCount;
        this.laDownvoteCount = laDownvoteCount;
        this.laUserRating = laUserRating;
        this.laAdminRating = laUserRating;
    }

    public Long getLaRatingId() {
        return laRatingId;
    }

    public void setLaRatingId(Long laRatingId) {
        this.laRatingId = laRatingId;
    }

    public Long getLaUpvoteCount() {
        return laUpvoteCount;
    }

    public void setLaUpvoteCount(Long laUpvoteCount) {
        this.laUpvoteCount = laUpvoteCount;
    }
    
    public Long getLaDownvoteCount() {
        return laDownvoteCount;
    }
    
    public void setLaDownvoteCount(Long laDownvoteCount) {
        this.laDownvoteCount = laDownvoteCount;
    }
    
    public Long getLaUserRating() {
        return laUserRating;
    }

    public void setLaUserRating(Long laUserRating) {
        this.laUserRating = laUserRating;
    }
    
    public Long getLaAdminRating() {
        return laAdminRating;
    }
    
    public void setLaAdminRating(Long laAdminRating) {
        this.laAdminRating = laAdminRating;
    }
    
    public LaLearnCourse getLaLearnCourse() {
        return laLearnCourse;
    }

    public void setLaLearnCourse(LaLearnCourse laLearnCourse) {
        this.laLearnCourse = laLearnCourse;
    }

}
