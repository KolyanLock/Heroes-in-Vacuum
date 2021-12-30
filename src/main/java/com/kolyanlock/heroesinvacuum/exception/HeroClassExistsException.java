package com.kolyanlock.heroesinvacuum.exception;

public class HeroClassExistsException extends CustomEntityExistsException {
    public HeroClassExistsException(String title) {
        super("Hero Class with title " + title + " already exists!");
    }
}
