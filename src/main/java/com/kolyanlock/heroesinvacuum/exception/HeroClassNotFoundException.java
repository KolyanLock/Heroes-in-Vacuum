package com.kolyanlock.heroesinvacuum.exception;

public class HeroClassNotFoundException extends CustomEntityNotFoundException {

    public HeroClassNotFoundException(int id) {
        super("Hero Class with id = " + id + " not found!");
    }

    public HeroClassNotFoundException(String title) {
        super("Hero Class with title " + title + " not found!");
    }
}
