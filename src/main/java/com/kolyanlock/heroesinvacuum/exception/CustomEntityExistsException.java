package com.kolyanlock.heroesinvacuum.exception;

public class CustomEntityExistsException extends RuntimeException {
    public CustomEntityExistsException(String message) {
        super(message);
    }
}
