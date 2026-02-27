package com.example.app.common.exception;

public class OidcAuthFailedException extends RuntimeException {
    public OidcAuthFailedException(String message) {
        super(message);
    }
}
