package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by amernath v on 2019-09-04.
 */
@Repository
public interface LaUserRepository extends JpaRepository<LaLearnUser, Long> {
	Optional<LaLearnUser> findByLaUserName(String laUserName);

	Optional<LaLearnUser> findByLaMailId(String laMailId);
    
    Optional<LaLearnUser> findByLaPhoneNumber(Long laPhoneNumber);

    Boolean existsByLaUserName(String laUserName);

    Boolean existsByLaMailId(String laMailId);
    
    Boolean existsByLaPhoneNumber(Long laPhoneNumber);
    
    List<LaLearnUser> findByLaUserIdIn(List<Long> laUserIds);
    
    Optional<LaLearnUser> findByLaUserNameOrLaMailId(String laUserName, String laMailId);
    
    Optional<LaLearnUser> findByLaUserNameOrLaPhoneNumber(String laUserName, Long laPhoneNumber);
    
    Optional<LaLearnUser> findByLaUserNameOrLaMailIdOrLaPhoneNumber(String laUserName, String laMailId, Long laPhoneNumber);
}
