package com.eventura.Exception;

public class VenueNotFoundException extends RuntimeException {
    public VenueNotFoundException(String message) {
        super(message);
    }
}
