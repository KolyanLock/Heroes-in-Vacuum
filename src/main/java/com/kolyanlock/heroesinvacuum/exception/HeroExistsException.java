package com.kolyanlock.heroesinvacuum.exception;

public class HeroExistsException extends CustomEntityExistsException {
    public HeroExistsException(String name) {
        super("Hero with name " + name + " already exists!");
    }
}
