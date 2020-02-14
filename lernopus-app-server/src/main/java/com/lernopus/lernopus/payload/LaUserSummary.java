package com.lernopus.lernopus.payload;

public class LaUserSummary {
    private Long laUserId;
    private String laUserName;
    private String laUserFullName;
    private String laImagePath;

    public LaUserSummary(Long laUserId, String laUserName, String laUserFullName, String laImagePath) {
        this.laUserId = laUserId;
        this.laUserName = laUserName;
        this.laUserFullName = laUserFullName;
        this.laImagePath = laImagePath;
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
    public String getLaImagePath() {
        return laImagePath;
    }

    public void setLaImagePath(String laImagePath) {
        this.laImagePath = laImagePath;
    }

}
