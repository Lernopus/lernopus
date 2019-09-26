package com.lernopus.lernopus.security;

import com.lernopus.lernopus.model.LaLearnUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LaUserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long laUserId;

    private String laUserName;

    private String laUserFullName;

    @JsonIgnore
    private String laMailId;

    @JsonIgnore
    private String laPassword;
    
    @JsonIgnore
    private Long laPhoneNumber;
    
    @JsonIgnore
    private String laImagePath;

    private Collection<? extends GrantedAuthority> authorities;

    public LaUserPrincipal(Long laUserId, String laUserName, String laUserFullName, String laMailId, String laPassword, Long laPhoneNumber, String laImagePath, Collection<? extends GrantedAuthority> authorities) {
        this.laUserId = laUserId;
        this.laUserName = laUserName;
        this.laUserFullName = laUserFullName;
        this.laMailId = laMailId;
        this.laPassword = laPassword;
        this.laPhoneNumber = laPhoneNumber;
        this.laImagePath = laImagePath;
        this.authorities = authorities;
    }

    public static LaUserPrincipal create(LaLearnUser user) {
        List<GrantedAuthority> authorities = user.getLaRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getLaRoleName().name())
        ).collect(Collectors.toList());

        return new LaUserPrincipal(
                user.getLaUserId(),
                user.getLaUserName(),
                user.getLaUserFullName(),
                user.getLaMailId(),
                user.getLaPassword(),
                user.getLaPhoneNumber(),
                user.getLaImagePath(),
                authorities
        );
    }

    public Long getLaUserId() {
        return laUserId;
    }

    public String getLaUserFullName() {
        return laUserFullName;
    }

    public String getLaMailId() {
        return laMailId;
    }
    
    public Long getLaPhoneNumber() {
        return laPhoneNumber;
    }
    
    public String getLaImagePath() {
        return laImagePath;
    }

    @Override
    public String getUsername() {
        return laUserName;
    }

    @Override
    public String getPassword() {
        return laPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	System.out.println("Authority Fetched : --------------------------------------- :" + authorities);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaUserPrincipal that = (LaUserPrincipal) o;
        return Objects.equals(laUserId, that.laUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(laUserId);
    }
}
