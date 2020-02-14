package com.lernopus.lernopus.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.lernopus.lernopus.model.audit.LaUserDateAudit;

/**
 * Created by amernath v on 2019-09-05.
 */
@Entity
@Table(name = "la_learn_course")
public class LaLearnCourse extends LaUserDateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "la_corse_id")
    private Long laCourseId;
	
	@Column(name = "la_corse_parent_id")
    private Long laCourseParentId;
	
	@Column(name = "la_corse_root_id")
    private Long laCourseRootId;

    @NotBlank
    @Size(max = 250)
    @Column(name = "la_course_name")
    private String laCourseName;
    
    @NotBlank
    @Lob
    @Column(name = "la_course_content_text")
    private String laCourseContentText;
    
    @NotBlank
    @Lob
    @Column(name = "la_course_content_html")
    private String laCourseContentHtml;

    @NotBlank
    @Size(max = 8)
    @Column(name = "la_author_id")
    private String laAuthorId;
    
    @Column(name = "la_is_note")
    private Boolean laIsNote;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "laAuthor")
    private Set<LaLearnUser> laAuthorSubscription = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "laCourse")
    private Set<LaLearnUser> laCourseSubscription = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "la_learn_course_tech",
            joinColumns = { @JoinColumn(name = "la_course_id") },
            inverseJoinColumns = { @JoinColumn(name = "la_tech_id") })
    private Set<LaLearnTechnology> laTechTag = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY, 
    		mappedBy = "laLearnCourse")
    private List<LaLearnAttachments> laLearnAttachments = new LinkedList<>();
    
    public LaLearnCourse(String laCourseName , String laAuthorId, Boolean laIsNote, String laCourseContentText, String laCourseContentHtml, List<LaLearnAttachments> laLearnAttachments, Long laCourseParentId, Long laCourseRootId) {
        this.laCourseName = laCourseName;
        this.laAuthorId = laAuthorId;
        this.laIsNote = laIsNote;
        this.laCourseContentHtml = laCourseContentHtml;
        this.laCourseContentText = laCourseContentText;
        this.laLearnAttachments = laLearnAttachments;
        this.laCourseParentId = laCourseParentId;
        this.laCourseRootId = laCourseRootId;
        
    }
    
    public LaLearnCourse() {

    }


    public Long getLaCourseId() {
        return laCourseId;
    }

    public void setLaCourseId(Long laCourseId) {
        this.laCourseId = laCourseId;
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

    public String getLaCourseName() {
        return laCourseName;
    }

    public void setLaCourseName(String laCourseName) {
        this.laCourseName = laCourseName;
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
    
    public Set<LaLearnUser> getLaAuthorSubscription() {
        return laAuthorSubscription;
    }
    
    public void setLaAuthorSubscription(Set<LaLearnUser> laAuthorSubscription) {
        this.laAuthorSubscription = laAuthorSubscription;
    }
    
    public Set<LaLearnUser> getLaCourseSubscription() {
        return laCourseSubscription;
    }
    
    public void setLaCourseSubscription(Set<LaLearnUser> laCourseSubscription) {
        this.laCourseSubscription = laCourseSubscription;
    }
    
    public Set<LaLearnTechnology> getLaTechTag() {
        return laTechTag;
    }
    
    public void setLaTechTag(Set<LaLearnTechnology> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public List<LaLearnAttachments> getLaLearnAttachments() {
        return laLearnAttachments;
    }
    
    public void setLaLearnAttachments(List<LaLearnAttachments> laLearnAttachments) {
        this.laLearnAttachments = laLearnAttachments;
    }
    
    public void addLaLearnAttachment(LaLearnAttachments laLearnAttachment) {
        this.laLearnAttachments.add(laLearnAttachment);
        laLearnAttachment.setLaLearnCourse(this);
    }

    public void removeLaLearnAttachment(LaLearnAttachments laLearnAttachment) {
        this.laLearnAttachments.remove(laLearnAttachment);
        laLearnAttachment.setLaLearnCourse(null);
    }



}
