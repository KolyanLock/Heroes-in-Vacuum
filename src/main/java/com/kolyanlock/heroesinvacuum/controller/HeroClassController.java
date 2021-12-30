package com.kolyanlock.heroesinvacuum.controller;

import com.kolyanlock.heroesinvacuum.dto.HeroClassDTO;
import com.kolyanlock.heroesinvacuum.service.HeroClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class HeroClassController {
    private final HeroClassService heroClassService;

    @GetMapping
    public List<HeroClassDTO> getAllHeroClasses(Sort sort) {
        return heroClassService.getAllHeroClasses(sort);
    }

    @GetMapping("/{id}")
    public HeroClassDTO getHeroClassById(@PathVariable int id) {
        return heroClassService.getHeroClassById(id);
    }

    @PostMapping("/create")
    public HeroClassDTO createHeroClass(@Valid @RequestBody HeroClassDTO heroClassDTO) {
        return heroClassService.createHeroClass(heroClassDTO);
    }

    @PutMapping("/{id}")
    public HeroClassDTO updateHeroClass(@PathVariable int id, @Valid @RequestBody HeroClassDTO heroClassDTO) {
        return heroClassService.updateHeroClass(id, heroClassDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteHeroClassByID(@PathVariable int id) {
        return heroClassService.deleteHeroClassById(id);
    }
}
