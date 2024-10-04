package com.eventura.Exception;

public class NoSeatAvailableException extends RuntimeException {
    public NoSeatAvailableException(String message) {
        super(message);
    }
}
