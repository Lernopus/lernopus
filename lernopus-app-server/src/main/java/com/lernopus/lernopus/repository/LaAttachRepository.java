package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaCourseAttachFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaAttachRepository extends JpaRepository<LaCourseAttachFile, String> {

}
