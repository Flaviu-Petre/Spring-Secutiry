package com.Spring_Security.Authorization.exception;

import org.springframework.http.HttpStatus;

public class InvalidDetailsException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidDetailsException(String message) {
        super(message);
    }

    public InvalidDetailsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() { return status; }
}
