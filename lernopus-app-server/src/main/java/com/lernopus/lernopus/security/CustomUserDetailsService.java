package com.lernopus.lernopus.security;

import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.repository.LaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amernath v on 2019-09-01.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    LaUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        LaLearnUser user = userRepository.findByLaUserNameOrLaMailId(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return LaUserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        LaLearnUser user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return LaUserPrincipal.create(user);
    }
}