package com.lernopus.lernopus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_technology")
public class LaLearnTechnology {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_tech_id")
    private Long laTechId;

	@Column(name = "la_tech_group")
    @Size(max = 250)
    private String laTechGroup;
    
    @Size(max = 250)
    @Column(name = "la_tech_category")
    private String laTechCategory;
    
    @Column(name = "id")
    @Size(max = 250)
    @NotNull
    private String id;
    
    @Size(max = 250)
    @NotNull
    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "laTechTag")
    private Set<LaLearnCourse> laLearnCourse = new HashSet<>();
    
    public LaLearnTechnology() {

    }

    public LaLearnTechnology(String laTechGroup , String laTechCategory) {
        this.laTechGroup = laTechGroup;
        this.laTechCategory = laTechCategory;
    }

    public Long getTechId() {
        return laTechId;
    }

    public void setLaTechId(Long laTechId) {
        this.laTechId = laTechId;
    }

    public String getTechGroup() {
        return laTechGroup;
    }

    public void setLaTechGroup(String laTechGroup) {
        this.laTechGroup = laTechGroup;
    }
    
    public String getLaTechCategory() {
        return laTechCategory;
    }
    
    public void setLaTechCategory(String laTechCategory) {
        this.laTechCategory = laTechCategory;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<LaLearnCourse> getLaLearnCourse() {
        return laLearnCourse;
    }
    
    public void setLaLearnCourse(Set<LaLearnCourse> laLearnCourse) {
        this.laLearnCourse = laLearnCourse;
    }

    
}
