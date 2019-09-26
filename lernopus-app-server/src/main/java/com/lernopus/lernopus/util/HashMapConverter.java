package com.lernopus.lernopus.util;
import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {
 
    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {
 
        String customerInfoJson = null;
        try {
            customerInfoJson = new ObjectMapper().writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
        	System.out.println("JSON writing error" + e);
        }
 
        return customerInfoJson;
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {
 
        Map<String, Object> customerInfo = null;
        try {
            customerInfo = new ObjectMapper().readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
        	System.out.println("JSON reading error" + e);
        }
 
        return customerInfo;
    }
 
}