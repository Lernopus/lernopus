package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnRole;
import com.lernopus.lernopus.model.LaRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by amernath v on 2019-09-05.
 */
@Repository
public interface LaRoleRepository extends JpaRepository<LaLearnRole, Long> {
    Optional<LaLearnRole> findByLaRoleName(LaRoleName roleName);
}
