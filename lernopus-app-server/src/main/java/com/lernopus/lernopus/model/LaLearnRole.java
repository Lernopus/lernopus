package com.lernopus.lernopus.model;

import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_role")
public class LaLearnRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_role_id")
    @Size(max = 20)
    private Long laRoleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "la_role_name")
    @Size(max = 20)
    private LaRoleName laRoleName;
    
    @NotBlank
    @Size(max = 250)
    @Column(name = "la_role_description")
    private String laRoleDescription;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            },
            mappedBy = "laRoles")
    private Set<LaLearnUser> laLearnUser = new HashSet<>();

    public LaLearnRole() {

    }

    public LaLearnRole(LaRoleName laRoleName , String laRoleDescription) {
        this.laRoleName = laRoleName;
        this.laRoleDescription = laRoleDescription;
    }

    public Long getRoleId() {
        return laRoleId;
    }

    public void setLaRoleId(Long laRoleId) {
        this.laRoleId = laRoleId;
    }

    public LaRoleName getLaRoleName() {
        return laRoleName;
    }

    public void setLaRoleName(LaRoleName laRoleName) {
        this.laRoleName = laRoleName;
    }
    
    public String getLaRoleDescription() {
        return laRoleDescription;
    }
    
    public void setLaRoleDescription(String laRoleDescription) {
        this.laRoleDescription = laRoleDescription;
    }
    
    public Set<LaLearnUser> getLaLearnUser() {
        return laLearnUser;
    }
    
    public void setLaLearnUser(Set<LaLearnUser> laLearnUser) {
        this.laLearnUser = laLearnUser;
    }

}
