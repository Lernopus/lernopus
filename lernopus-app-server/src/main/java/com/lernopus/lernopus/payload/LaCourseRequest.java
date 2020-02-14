package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lernopus.lernopus.model.LaLearnAttachments;
import com.lernopus.lernopus.model.LaLearnTechnology;

import java.util.List;
import java.util.Set;

public class LaCourseRequest {
    @NotBlank
    @Size(max = 250)
    private String laCourseName;
    
    @Valid
    @NotNull
    private String laCourseContentText;
    
    @Valid
    @NotNull
    private String laCourseContentHtml;

    @NotNull
    @Valid
    private List<LaLearnAttachments> laLearnAttachments;
    
    @Size(max = 8)
    @Valid
    @NotNull
    private String laAuthorId;
    
    @Valid
    @NotNull
    private Boolean laIsNote;
    
    @Valid
    @NotNull
    private Set<LaLearnTechnology> laTechTag;
    
    @Valid
    private Long laCourseParentId;
    
    @Valid
    private Long laCourseRootId;
    

    public String getLaCourseName() {
        return laCourseName;
    }

    public void setLaCourseName(String laCourseName) {
        this.laCourseName = laCourseName;
    }

    public String getLaAuthorId() {
        return laAuthorId;
    }

    public void setLaAuthorId(String laAuthorId) {
        this.laAuthorId = laAuthorId;
    }

    public Boolean getLaIsNote() {
        return laIsNote;
    }

    public void setLaIsNote(Boolean laIsNote) {
        this.laIsNote = laIsNote;
    }
    
    public List<LaLearnAttachments> getLaLearnAttachments() {
        return laLearnAttachments;
    }

    public void setLaLearnAttachments(List<LaLearnAttachments> laLearnAttachments) {
        this.laLearnAttachments = laLearnAttachments;
    }
    
    public Set<LaLearnTechnology> getLaTechTag() {
        return laTechTag;
    }

    public void setLaTechTag(Set<LaLearnTechnology> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public String getLaCourseContentHtml() {
        return laCourseContentHtml;
    }

    public void setLaCourseContentHtml(String laCourseContentHtml) {
        this.laCourseContentHtml = laCourseContentHtml;
    }
    
    public String getLaCourseContentText() {
        return laCourseContentText;
    }

    public void setLaCourseContentText(String laCourseContentText) {
        this.laCourseContentText = laCourseContentText;
    }
    
    public Long getLaCourseParentId() {
        return laCourseParentId;
    }

    public void setLaCourseParentId(Long laCourseParentId) {
        this.laCourseParentId = laCourseParentId;
    }
    
    public Long getLaCourseRootId() {
        return laCourseRootId;
    }

    public void setLaCourseRootId(Long laCourseRootId) {
        this.laCourseRootId = laCourseRootId;
    }

}
