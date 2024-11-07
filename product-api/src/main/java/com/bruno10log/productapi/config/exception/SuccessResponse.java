package com.bruno10log.productapi.config.exception;

import org.springframework.http.HttpStatus;

public record SuccessResponse(int status, String message) {

    public static SuccessResponse create(String message) {
        return new SuccessResponse(HttpStatus.OK.value(), message);
    }

}
