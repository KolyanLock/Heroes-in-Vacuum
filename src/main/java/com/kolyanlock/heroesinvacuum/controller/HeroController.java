package com.kolyanlock.heroesinvacuum.controller;

import com.kolyanlock.heroesinvacuum.dto.HeroDTO;
import com.kolyanlock.heroesinvacuum.dto.HeroSearchDTO;
import com.kolyanlock.heroesinvacuum.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping
    public Page<HeroDTO> getAllHeroes(@PageableDefault(sort = "name") Pageable pageable) {
        return heroService.getAllHeroes(pageable);
    }

    @PostMapping("/search")
    public Page<HeroDTO> searchHeroes(@RequestBody HeroSearchDTO heroSearchDTO,
                                     @PageableDefault(sort = "name") Pageable pageable){
        return heroService.searchHero(pageable, heroSearchDTO);
    }

    @GetMapping("/{id}")
    public HeroDTO getHero(@PathVariable long id) {
        return heroService.getHeroById(id);
    }

    @PostMapping("/create")
    public HeroDTO createNewHero(@Valid @RequestBody HeroDTO heroDTO) {
        return heroService.createNewHero(heroDTO);
    }

    @PutMapping("/{id}")
    public HeroDTO updateHero(@PathVariable long id, @Valid @RequestBody HeroDTO heroDTO) {
        return heroService.updateHero(id, heroDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteHero(@PathVariable long id) {
        return heroService.deleteHero(id);
    }


    @GetMapping("/classes/{heroClass}")
    public Page<HeroDTO> getAllHeroesByHeroClass(@PathVariable String heroClass,
                                                 @PageableDefault Pageable pageable) {
        return heroService.findAllByHeroClassTitle(heroClass, pageable);
    }

    @GetMapping("/clubs/{club}")
    public Page<HeroDTO> getAllHeroesByClub(@PathVariable String club,
                                                 @PageableDefault Pageable pageable) {
        return heroService.findAllByClub(club, pageable);
    }
}
