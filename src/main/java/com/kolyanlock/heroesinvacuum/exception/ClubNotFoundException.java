package com.kolyanlock.heroesinvacuum.exception;

import com.kolyanlock.heroesinvacuum.dto.ClubDTO;

import java.util.List;

public class ClubNotFoundException extends CustomEntityNotFoundException {

    public ClubNotFoundException(int id) {
        super("Club with id = " + id + " not found!");
    }

    public ClubNotFoundException(List<String> clubTitleList) {
        super("Incorrect club list: " + clubTitleList);
    }
}
