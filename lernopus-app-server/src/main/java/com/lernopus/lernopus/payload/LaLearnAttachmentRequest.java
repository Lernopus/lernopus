package com.lernopus.lernopus.payload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Convert;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lernopus.lernopus.util.HashMapConverter;

public class LaLearnAttachmentRequest {
	@Valid
	@NotNull
    private String laAttachPath;
    
    @Valid
    @NotNull
    private String laAttachName;
    
    @Valid
    @NotNull
    private String laAttachType;
    
    @Valid
    @NotNull
    private Long laAttachSize;
    
    @Valid
    @NotNull
    private String laAttachExtension;
    
    @Valid
    @NotNull
    private String laAttachFileId;
    
    @Valid
    @NotNull
    private String laAttachPreview;
    
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> laAttachPreviewMap;
    
    @Valid
    @NotNull
    private String laAttachSizeReadable;
    
    @Valid
    @NotNull
    private String laAttachBlob;
    
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> laAttachBlobMap;

    public String getLaAttachmentPath() {
        return laAttachPath;
    }

    public void setLaAttachmentPath(String laAttachmentPath) {
        this.laAttachPath = laAttachmentPath;
    }
    
    public String getLaAttacName() {
        return laAttachName;
    }

    public void setLaAttachName(String laAttachmentName) {
        this.laAttachName = laAttachmentName;
    }
    
    public String getLaAttachmentType() {
        return laAttachType;
    }

    public void setLaAttachmentType(String laAttachmentType) {
        this.laAttachType = laAttachmentType;
    }
    
    public Long getLaAttachmentSize() {
        return laAttachSize;
    }

    public void setLaAttachmentSize(Long laAttachmentSize) {
        this.laAttachSize = laAttachmentSize;
    }
    
    public String getLaAttachExtension() {
        return laAttachExtension;
    }

    public void setLaAttachExtension(String laAttachExtension) {
        this.laAttachExtension = laAttachExtension;
    }
    
    public String getLaAttachFileId() {
        return laAttachFileId;
    }

    public void setLaAttachFileId(String laAttachFileId) {
        this.laAttachFileId = laAttachFileId;
    }
    
    public String getLaAttachPreview() {
//        return laAttachPreview;
    	try {
			return new ObjectMapper().writeValueAsString(laAttachPreviewMap);
		} catch (JsonProcessingException e) {
			return "";
		}
    }

    public void setLaAttachPreview(String laAttachPreview) {
        this.laAttachPreview = laAttachPreview;
    }
    
    @SuppressWarnings("unchecked")
	public Map<String, Object> getLaAttachPreviewMap() {
    	try {
    		return new ObjectMapper().readValue(laAttachPreview, HashMap.class);
		} catch (IOException e2) {
			return new HashMap<>();
		}
    }

    public void setLaAttachPreviewMap(Map<String, Object> laAttachPreviewMap) {
        this.laAttachPreviewMap = laAttachPreviewMap;
        try {
			this.laAttachPreview = new ObjectMapper().writeValueAsString(laAttachPreviewMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public String getLaAttachSizeReadable() {
        return laAttachSizeReadable;
    }

    public void setLaAttachSizeReadable(String laAttachSizeReadable) {
        this.laAttachSizeReadable = laAttachSizeReadable;
    }
    
    public String getLaAttachBlob() {
        try {
			return new ObjectMapper().writeValueAsString(laAttachBlobMap);
		} catch (JsonProcessingException e) {
			return "";
		}
    }

    public void setLaAttachBlob(String laAttachBlob) {
        this.laAttachBlob = laAttachBlob;
    }
    
    @SuppressWarnings("unchecked")
	public Map<String, Object> getLaAttachBlobMap() {
    	try {
    		return new ObjectMapper().readValue(this.laAttachBlob, HashMap.class);
		} catch (IOException e2) {
			return new HashMap<>();
		}
    }

    public void setLaAttachBlobMap(Map<String, Object> laAttachBlobMap) {
        this.laAttachBlobMap = laAttachBlobMap;
        try {
			this.laAttachBlob = new ObjectMapper().writeValueAsString(laAttachBlobMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
