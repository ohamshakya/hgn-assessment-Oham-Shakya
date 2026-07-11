package com.himalaya.task.common.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ErrorResponse {
    public static ResponseEntity<ResponseWrapper<String>> buildErrorResponse(String message, HttpStatus status) {
        ResponseWrapper<String> stringWrapper = new ResponseWrapper<>(null, message, status.value(), false);
        return ResponseEntity.status(status).body(stringWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> buildErrorResponse(T data, String message, HttpStatus status) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(data, message, status.value(), false);
        return ResponseEntity.status(status).body(responseWrapper);
    }

    public static void writeErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        ResponseWrapper<String> stringWrapper = new ResponseWrapper<>(null, message, status.value(), false);
        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(stringWrapper);
        response.getWriter().write(jsonResponse);
    }
}
