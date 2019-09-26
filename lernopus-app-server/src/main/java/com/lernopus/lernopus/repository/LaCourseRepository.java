package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by amernath v on 2019-09-05.
 */
@Repository
public interface LaCourseRepository extends JpaRepository<LaLearnCourse, Long> {

    Optional<LaLearnCourse> findByLaCourseId(Long courseId);

    Page<LaLearnCourse> findByLaCreatedUser(Long userId, Pageable pageable);

    long countByLaCreatedUser(Long userId);

    List<LaLearnCourse> findByLaCourseIdIn(List<Long> courseIds);

    List<LaLearnCourse> findByLaCourseIdIn(List<Long> courseIds, Sort sort);
}
