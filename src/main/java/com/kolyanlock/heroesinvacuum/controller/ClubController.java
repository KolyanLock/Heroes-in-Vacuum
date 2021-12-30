package com.kolyanlock.heroesinvacuum.controller;

import com.kolyanlock.heroesinvacuum.dto.ClubDTO;
import com.kolyanlock.heroesinvacuum.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @GetMapping
    public List<ClubDTO> getAllClubs(Sort sort) {
        return clubService.getAllClubs(sort);
    }

    @GetMapping("/{id}")
    public ClubDTO getClubById(@PathVariable int id) {
        return clubService.getClubById(id);
    }

    @PostMapping("/create")
    public ClubDTO createClub(@Valid @RequestBody ClubDTO clubDTO) {
        return clubService.createClub(clubDTO);
    }

    @PutMapping("/{id}")
    public ClubDTO updateClub(@PathVariable int id, @Valid @RequestBody ClubDTO clubDTO) {
        return clubService.updateClub(id, clubDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteClubByID(@PathVariable int id) {
        return clubService.deleteClubById(id);
    }
}
