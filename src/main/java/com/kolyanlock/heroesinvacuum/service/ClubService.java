package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dto.ClubDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ClubService {
    List<ClubDTO> getAllClubs(Sort sort);

    ClubDTO getClubById(int id);

    ClubDTO createClub(ClubDTO clubDTO);

    ClubDTO updateClub(int id, ClubDTO clubDTO);

    String deleteClubById(int id);
}
