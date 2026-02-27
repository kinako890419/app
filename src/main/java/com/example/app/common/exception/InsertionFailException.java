package com.example.app.common.exception;

public class InsertionFailException extends RuntimeException {
    public InsertionFailException(String message) {
        super(message);
    }
}
