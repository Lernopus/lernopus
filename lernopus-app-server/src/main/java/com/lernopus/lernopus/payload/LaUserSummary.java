package com.lernopus.lernopus.payload;

public class LaUserSummary {
    private Long laUserId;
    private String laUserName;
    private String laUserFullName;

    public LaUserSummary(Long laUserId, String laUserName, String laUserFullName) {
        this.laUserId = laUserId;
        this.laUserName = laUserName;
        this.laUserFullName = laUserFullName;
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
}
