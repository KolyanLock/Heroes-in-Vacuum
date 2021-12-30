package com.kolyanlock.heroesinvacuum.exception;

public class ClubExistsException extends CustomEntityExistsException {
    public ClubExistsException(String title) {
        super("Club with title " + title + " already exists!");
    }
}
