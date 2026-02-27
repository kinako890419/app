package com.example.app.common.exception;

public class SamlAuthFailedException extends RuntimeException {
    public SamlAuthFailedException(String message) {
        super(message);
    }
}
