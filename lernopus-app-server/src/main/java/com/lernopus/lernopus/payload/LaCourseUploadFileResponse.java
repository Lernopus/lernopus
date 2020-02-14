package com.lernopus.lernopus.payload;


public class LaCourseUploadFileResponse {
    private String laFileDownloadUri;

    public LaCourseUploadFileResponse(String laFileDownloadUri) {
        this.laFileDownloadUri = laFileDownloadUri;
    }

    public String getLaFileDownloadUri() {
        return laFileDownloadUri;
    }

    public void setLaFileDownloadUri(String laFileDownloadUri) {
        this.laFileDownloadUri = laFileDownloadUri;
    }
}
