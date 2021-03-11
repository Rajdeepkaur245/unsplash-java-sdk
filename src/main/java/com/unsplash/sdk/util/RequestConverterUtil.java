package com.unsplash.sdk.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Converts Request Object to a Map of parameters.
 */
public class RequestConverterUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Converts Request object to Map of parameters for API call.
     *
     * @param request Request object.
     * @return Map with parameters name/value pairs to send to the API.
     */
    public static Map<String, Object> convert(final Object request) {
        return objectMapper.convertValue(request, Map.class);
    }
}
