package com.lernopus.lernopus.model;

import com.lernopus.lernopus.model.audit.LaDateAudit;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amernath v on 2019-09-04.
 */

@Entity
@Table(name = "la_learn_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "la_user_name"
        }),
        @UniqueConstraint(columnNames = {
            "la_mail_id"
        }),
        @UniqueConstraint(columnNames = {
        	"la_phone_number"
        })
})
public class LaLearnUser extends LaDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_user_id")
    private Long laUserId;

    @NotBlank
    @Column(name = "la_user_name")
    @Size(max = 8)
    private String laUserName;

    @NotBlank
    @Column(name = "la_user_full_name")
    @Size(max = 90)
    private String laUserFullName;

    @NaturalId
    @NotBlank
    @Column(name = "la_mail_id")
    @Size(max = 100)
    @Email
    private String laMailId;
    
    @Column(name = "la_phone_number")
    private Long laPhoneNumber;

    @NotBlank
    @Column(name = "la_password")
    private String laPassword;
    
    @Lob
    @Column(name = "la_image_path")
    private String laImagePath;
    
    @Column(nullable = false)
    private Boolean emailVerified = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE
        })
    @JoinTable(name = "la_learn_user_roles",
            joinColumns = @JoinColumn(name = "la_user_id"),
            inverseJoinColumns = @JoinColumn(name = "la_role_id"))
    private Set<LaLearnRole> laRoles = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name = "la_learn_author_subscription",
            joinColumns = { @JoinColumn(name = "la_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "la_author_id") })
    private Set<LaLearnCourse> laAuthor = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name = "la_learn_course_subscription",
            joinColumns = { @JoinColumn(name = "la_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "la_course_id") })
    private Set<LaLearnCourse> laCourse = new HashSet<>();

    public LaLearnUser() {

    }

    public LaLearnUser(String laUserName, String laUserFullName, String laMailId, Long laPhoneNumber, String laImagePath, String laPassword) {
    	this.laUserName = laUserName;
    	this.laUserFullName = laUserFullName;
    	this.laMailId = laMailId;
    	this.laPhoneNumber = laPhoneNumber;
    	this.laImagePath = laImagePath;
    	this.laPassword = laPassword;
    }
    
    public Long getLaUserId() {
        return laUserId;
    }

    public void setLaUserId(Long laUserId) {
        this.laUserId = laUserId;
    }

    public String getLaUserName() {
        return laUserName;
    }

    public void setLaUserName(String laUserName) {
        this.laUserName = laUserName;
    }

    public String getLaUserFullName() {
        return laUserFullName;
    }

    public void setLaUserFullName(String laUserFullName) {
        this.laUserFullName = laUserFullName;
    }

    public String getLaMailId() {
        return laMailId;
    }

    public void setLaMailId(String laMailId) {
        this.laMailId = laMailId;
    }
    
    public Long getLaPhoneNumber() {
        return laPhoneNumber;
    }

    public void setLaPhoneNumber(Long laPhoneNumber) {
        this.laPhoneNumber = laPhoneNumber;
    }
    
    public String getLaImagePath() {
        return laImagePath;
    }

    public void setLaImagePath(String laImagePath) {
        this.laImagePath = laImagePath;
    }


    public String getLaPassword() {
        return laPassword;
    }

    public void setLaPassword(String laPassword) {
        this.laPassword = laPassword;
    }

    public Set<LaLearnRole> getLaRoles() {
        return laRoles;
    }

    public void setLaRoles(Set<LaLearnRole> roles) {
        this.laRoles = roles;
    }
    
    public Set<LaLearnCourse> getLaAuthor() {
        return laAuthor;
    }

    public void setLaAuthor(Set<LaLearnCourse> laAuthor) {
        this.laAuthor = laAuthor;
    }
    
    public Set<LaLearnCourse> getLaCourse() {
        return laCourse;
    }

    public void setLaCourse(Set<LaLearnCourse> laCourse) {
        this.laCourse = laCourse;
    }
    
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    
    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}