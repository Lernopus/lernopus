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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_course_comments")
public class LaLearnCourseComments {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_comments_id")
    @Size(max = 20)
    private Long laCommentId;

    @NotBlank
    @Size(max = 250)
    @Column(name = "la_comment_content")
    private String laCommentContent;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnCourse laLearnCourse;

    public LaLearnCourseComments() {

    }

    public LaLearnCourseComments(String laCommentContent) {
        this.laCommentContent = laCommentContent;
    }

    public Long getCommentId() {
        return laCommentId;
    }

    public void setLaCommentId(Long laCommentId) {
        this.laCommentId = laCommentId;
    }

    public String getLaCommentContent() {
        return laCommentContent;
    }
    
    public void setLaCommentContent(String laCommentContent) {
        this.laCommentContent = laCommentContent;
    }
    
    public LaLearnCourse getLaLearnCourse() {
        return laLearnCourse;
    }
    
    public void setLaLearnCourse(LaLearnCourse laLearnCourse) {
        this.laLearnCourse = laLearnCourse;
    }

}
