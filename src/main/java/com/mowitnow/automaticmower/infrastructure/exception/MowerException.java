package com.mowitnow.automaticmower.infrastructure.exception;

public class MowerException extends RuntimeException {
    public MowerException(String message) {
        super(message);
    }

    public MowerException(String message, Throwable cause) {
        super(message, cause);
    }
}

