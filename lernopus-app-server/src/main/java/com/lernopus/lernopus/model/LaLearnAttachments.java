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

    @Lob
    @Column(name = "la_attach_path")
    private String laAttachPath;
    
    @Column(name = "la_attach_name")
    private String laAttachName;
    
    @Column(name = "la_attach_type")
    private String laAttachType;
    
    @Column(name = "la_attach_size")
    private Long laAttachSize;
    
    @Column(name = "la_attach_extension")
    private String laAttachExtension;
    
    @Column(name = "la_attach_file_id")
    private String laAttachFileId;
    
    
    @Lob
    @Column(name = "la_attach_preview")
    private String laAttachPreview;
    
//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> laAttachPreviewMap;
    
    @Column(name = "la_attach_size_readable")
    private String laAttachSizeReadable;
    
    @Lob
    @Column(name = "la_attach_blob")
    private String laAttachBlob;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_course_id", nullable = false)
    private LaLearnCourse laLearnCourse;

    public LaLearnAttachments() {

    }

    public LaLearnAttachments(String laAttachmentPath, String laAttachmentName, String laAttachmentType, Long laAttachmentSize, String laAttachExtension, String laAttachFileId, String laAttachPreview, String laAttachSizeReadable, String laAttachBlob) throws JsonProcessingException {
        this.laAttachPath = laAttachmentPath;
        this.laAttachName = laAttachmentName;
        this.laAttachType = laAttachmentType;
        this.laAttachSize = laAttachmentSize;
        this.laAttachExtension = laAttachExtension;
        this.laAttachFileId = laAttachFileId;
        this.laAttachPreview =  laAttachPreview;
        this.laAttachSizeReadable = laAttachSizeReadable;
        this.laAttachBlob = laAttachBlob;
    }

    public Long getId() {
        return laAttachId;
    }

    public void setId(Long id) {
        this.laAttachId = id;
    }

    public String getLaAttachmentPath() {
        return laAttachPath;
    }

    public void setLaAttachmentPath(String laAttachmentPath) {
        this.laAttachPath = laAttachmentPath;
    }
    
    public String getLaAttachName() {
        return laAttachName;
    }

    public void setLaAttachName(String laAttachmentName) {
        this.laAttachName = laAttachmentName;
    }
    
    public String getLaAttachmentType() {
        return laAttachType;
    }

    public void setLaAttachmentType(String laAttachmentType) {
        this.laAttachType = laAttachmentType;
    }
    
    public Long getLaAttachmentSize() {
        return laAttachSize;
    }

    public void setLaAttachmentSize(Long laAttachmentSize) {
        this.laAttachSize = laAttachmentSize;
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
    
    public String getLaAttachBlob() {
        return laAttachBlob;
    }

    public void setLaAttachBlob(Map<String,Object>  laAttachBlob) {
        try {
			this.laAttachBlob = new ObjectMapper().writeValueAsString(laAttachBlob);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
