package com.kolyanlock.heroesinvacuum.exception;

public class HeroNotFoundException extends CustomEntityNotFoundException {
    public HeroNotFoundException(Long id) {
        super("Hero with id = " + id + " not found!");
    }
}
