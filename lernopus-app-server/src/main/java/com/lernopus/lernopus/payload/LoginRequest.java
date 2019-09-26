package com.lernopus.lernopus.payload;

import javax.validation.constraints.NotBlank;

/**
 * Created by amernath v on 2019-09-04.
 */
public class LoginRequest {
    @NotBlank
    private String laUserNameOrLaMailId;

    @NotBlank
    private String laPassword;

    public String getLaUserNameOrLaMailId() {
        return laUserNameOrLaMailId;
    }

    public void setLaUserNameOrLaMailId(String laUserNameOrLaMailId) {
        this.laUserNameOrLaMailId = laUserNameOrLaMailId;
    }

    public String getLaPassword() {
        return laPassword;
    }

    public void setLaPassword(String laPassword) {
        this.laPassword = laPassword;
    }
}
