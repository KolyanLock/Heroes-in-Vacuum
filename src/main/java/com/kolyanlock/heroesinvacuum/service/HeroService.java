package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dto.HeroDTO;
import com.kolyanlock.heroesinvacuum.dto.HeroSearchDTO;
import com.kolyanlock.heroesinvacuum.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HeroService {
    Page<HeroDTO> getAllHeroes(Pageable pageable);

    Page<HeroDTO> searchHero(Pageable pageable, HeroSearchDTO heroSearchDTO);

    HeroDTO getHeroById(long id);

    HeroDTO createNewHero(HeroDTO heroDTO);

    HeroDTO updateHero(long id, HeroDTO heroDTO);

    String deleteHero(long id);

    Page<HeroDTO> findAllByHeroClassTitle(String heroClass, Pageable pageable);

    Page<HeroDTO> findAllByClub(String club, Pageable pageable);
}
