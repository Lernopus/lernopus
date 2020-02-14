package com.lernopus.lernopus.payload;

import java.time.Instant;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private Long courseCount;
    private Long voteCount;
    private String laImagePath;

    public UserProfile(Long id, String username, String name, Instant joinedAt, Long courseCount, Long voteCount, String laImagePath) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.courseCount = courseCount;
        this.voteCount = voteCount;
        this.laImagePath = laImagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Long getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Long courseCount) {
        this.courseCount = courseCount;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
    
    public String getlaImagePath() {
        return laImagePath;
    }

    public void setlaImagePath(String laImagePath) {
        this.laImagePath = laImagePath;
    }

}
