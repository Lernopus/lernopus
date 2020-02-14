package com.lernopus.lernopus.model;

import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by amernath v on 2019-09-04.
 */

@Entity
@Table(name = "la_learn_attachments")
public class LaLearnAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_attach_id")
    private Long laAttachId;

    @Column(name = "la_attach_name")
    private String laAttachName;
    
    @Column(name = "la_attach_extension")
    private String laAttachExtension;
    
    @Column(name = "la_attach_file_id")
    private String laAttachFileId;
    
    
    @Lob
    @Column(name = "la_attach_preview")
    private String laAttachPreview;
    
    @Column(name = "la_attach_size_readable")
    private String laAttachSizeReadable;
    
    @Column(name = "la_attach_file_ref_id")
    private String laAttachFileRefId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_course_id", nullable = false)
    private LaLearnCourse laLearnCourse;

    public LaLearnAttachments() {

    }

    public LaLearnAttachments(String laAttachmentName, String laAttachExtension, String laAttachFileId, String laAttachPreview, String laAttachSizeReadable, String laAttachFileRefId) throws JsonProcessingException {
        this.laAttachName = laAttachmentName;
        this.laAttachExtension = laAttachExtension;
        this.laAttachFileId = laAttachFileId;
        this.laAttachPreview =  laAttachPreview;
        this.laAttachSizeReadable = laAttachSizeReadable;
        this.laAttachFileRefId = laAttachFileRefId;
    }

    public Long getId() {
        return laAttachId;
    }

    public void setId(Long id) {
        this.laAttachId = id;
    }

    public String getLaAttachName() {
        return laAttachName;
    }

    public void setLaAttachName(String laAttachmentName) {
        this.laAttachName = laAttachmentName;
    }
    
    public String getLaAttachExtension() {
        return laAttachExtension;
    }

    public void setLaAttachExtension(String laAttachExtension) {
        this.laAttachExtension = laAttachExtension;
    }
    
    public String getLaAttachFileId() {
        return laAttachFileId;
    }

    public void setLaAttachFileId(String laAttachFileId) {
        this.laAttachFileId = laAttachFileId;
    }
    
    public String getLaAttachPreview() {
        return laAttachPreview;
    }

    public void setLaAttachPreview(Map<String,Object> laAttachPreview) {
        try {
			this.laAttachPreview = new ObjectMapper().writeValueAsString(laAttachPreview);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
    public String getLaAttachSizeReadable() {
        return laAttachSizeReadable;
    }

    public void setLaAttachSizeReadable(String laAttachSizeReadable) {
        this.laAttachSizeReadable = laAttachSizeReadable;
    }
    
    public String getLaAttachFileRefId() {
        return laAttachFileRefId;
    }

    public void setLaAttachFileRefId(String laAttachFileRefId) {
        this.laAttachFileRefId = laAttachFileRefId;
    }
    
    public LaLearnCourse getLaLearnCourse() {
        return laLearnCourse;
    }

    public void setLaLearnCourse(LaLearnCourse laLearnCourse) {
        this.laLearnCourse = laLearnCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaLearnAttachments choice = (LaLearnAttachments) o;
        return Objects.equals(laAttachId, choice.laAttachId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laAttachId);
    }
}
