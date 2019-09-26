package com.lernopus.lernopus.payload;

import javax.validation.constraints.*;

/**
 * Created by amernath v on 2019-09-04.
 */

public class SignUpRequest {
	
	@NotBlank
    @Size(min = 8, max = 8)
    private String laUserName;

    @NotBlank
    @Size(min = 3, max = 90)
    private String laUserFullName;

    @NotBlank
    @Size(max = 100)
    @Email
    private String laMailId;
    
    private Long laPhoneNumber;
    
    private String laImagePath;

    @NotBlank
    private String laPassword;
    
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
    
    public String getLaMailId() {
        return laMailId;
    }

    public void setLaMailId(String laMailId) {
        this.laMailId = laMailId;
    }
    
    public Long getLaPhoneNumber() {
        return laPhoneNumber;
    }

    public void setLaPhoneNumber(Long laPhoneNumber) {
        this.laPhoneNumber = laPhoneNumber;
    }

    public String getLaImagePath() {
        return laImagePath;
    }

    public void setLaImagePath(String laImagePath) {
        this.laImagePath = laImagePath;
    }

    public String getLaPassword() {
        return laPassword;
    }

    public void setLaPassword(String laPassword) {
        this.laPassword = laPassword;
    }
}
