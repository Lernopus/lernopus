package com.lernopus.lernopus.payload;

/**
 * Created by amernath v on 2019-09-07.
 */
public class LaApiResponse {
    private Boolean success;
    private String message;

    public LaApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
