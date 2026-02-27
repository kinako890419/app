package com.example.app.common.exception;

public class DeleteFailException extends RuntimeException {
    public DeleteFailException(String message) {
        super(message);
    }
}
