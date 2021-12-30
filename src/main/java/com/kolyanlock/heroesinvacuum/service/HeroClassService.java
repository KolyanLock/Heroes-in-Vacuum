package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dto.HeroClassDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface HeroClassService {
    List<HeroClassDTO> getAllHeroClasses(Sort sort);

    HeroClassDTO getHeroClassById(int id);

    HeroClassDTO createHeroClass(HeroClassDTO heroClassDTO);

    HeroClassDTO updateHeroClass(int id, HeroClassDTO heroClassDTO);

    String deleteHeroClassById(int id);
}
