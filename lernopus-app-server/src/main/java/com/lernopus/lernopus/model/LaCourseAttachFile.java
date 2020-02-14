package com.lernopus.lernopus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "la_course_attach_file")
public class LaCourseAttachFile {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String laCourseAttachId;
    
    private String laAttachName;

    private String laAttachType;

    @Lob
    private byte[] laCourseAttachData;

    public LaCourseAttachFile() {

    }

    public LaCourseAttachFile(String laCourseAttachId, String laAttachName, String laAttachType, byte[] laCourseAttachData) {
    	this.laCourseAttachId = laCourseAttachId;
    	this.laCourseAttachData = laCourseAttachData;
        this.laAttachName = laAttachName;
        this.laAttachType = laAttachType;
    }

    public String getLaCourseAttachId() {
        return laCourseAttachId;
    }

    public void setLaCourseAttachId(String laCourseAttachId) {
        this.laCourseAttachId = laCourseAttachId;
    }
    
    public String getLaAttachName() {
        return laAttachName;
    }

    public void setLaAttachName(String laAttachName) {
        this.laAttachName = laAttachName;
    }

    public String getLaAttachType() {
        return laAttachType;
    }

    public void setLaAttachType(String laAttachType) {
        this.laAttachType = laAttachType;
    }

    public byte[] getLaCourseAttachData() {
        return laCourseAttachData;
    }

    public void setLaCourseAttachData(byte[] laCourseAttachData) {
        this.laCourseAttachData = laCourseAttachData;
    }
}
