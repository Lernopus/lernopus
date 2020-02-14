package com.lernopus.lernopus.payload;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LaCourseResponse {
    private Long laLearnCourseId;
    private String laLearnCourseName;
    private List<Map<String,Object>> laLearnAttachments;
    private String laCourseContentText;
    private String laCourseContentHtml;
    private List<String> laTechTag;
    private LaUserSummary createdBy;
    private Instant laCreatedAt;
    private Boolean laIsNote;
    private Long laCourseParentId;
    private Long laCourseRootId;
    private PagedResponse<LaCourseResponse> childCoursePageResponse;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;
    private Long totalVotes;

    public Long getLearnCourseId() {
        return laLearnCourseId;
    }

    public void setLearnCourseId(Long laLearnCourseId) {
        this.laLearnCourseId = laLearnCourseId;
    }

    public String getLaLearnCourseName() {
        return laLearnCourseName;
    }

    public void setLaLearnCourseName(String laLearnCourseName) {
        this.laLearnCourseName = laLearnCourseName;
    }
    
    public List<Map<String, Object>> getLaLearnAttachments() {
        return laLearnAttachments;
    }

    public void setLaLearnAttachments(List<Map<String, Object>> laLearnAttachments) {
        this.laLearnAttachments = laLearnAttachments;
    }

    public LaUserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LaUserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getExpired() {
        return laIsNote;
    }

    public void setExpired(Boolean expired) {
        laIsNote = expired;
    }

    public Long getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(Long selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    public Instant getLaCreatedAt() {
        return laCreatedAt;
    }

    public void setLaCreatedAt(Instant laCreatedAt) {
        this.laCreatedAt = laCreatedAt;
    }
    
    public String getLaCourseContentText() {
        return laCourseContentText;
    }

    public void setLaCourseContentText(String laCourseContentText) {
        this.laCourseContentText = laCourseContentText;
    }
    
    public String getLaCourseContentHtml() {
        return laCourseContentHtml;
    }

    public void setLaCourseContentHtml(String laCourseContentHtml) {
        this.laCourseContentHtml = laCourseContentHtml;
    }
    
    public List<String> getLaTechTag() {
        return laTechTag;
    }
    
    public void setLaTechTag(List<String> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public Boolean getLaIsNote() {
        return laIsNote;
    }

    public void setLaIsNote(Boolean laIsNote) {
        this.laIsNote = laIsNote;
    }
    
    public Long getCourseParentId() {
        return laCourseParentId;
    }

    public void setCourseParentId(Long laCourseParentId) {
        this.laCourseParentId = laCourseParentId;
    }
    
    public Long getCourseRootId() {
        return laCourseRootId;
    }

    public void setCourseRootId(Long laCourseRootId) {
        this.laCourseRootId = laCourseRootId;
    }
    
    public PagedResponse<LaCourseResponse> getChildCoursePageResponse() {
        return childCoursePageResponse;
    }

    public void setChildCoursePageResponse(PagedResponse<LaCourseResponse> childCoursePageResponse) {
        this.childCoursePageResponse = childCoursePageResponse;
    }

}
