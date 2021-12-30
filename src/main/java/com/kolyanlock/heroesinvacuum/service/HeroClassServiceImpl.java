package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dao.HeroClassRepository;
import com.kolyanlock.heroesinvacuum.dto.HeroClassDTO;
import com.kolyanlock.heroesinvacuum.entity.HeroClass;
import com.kolyanlock.heroesinvacuum.exception.HeroClassExistsException;
import com.kolyanlock.heroesinvacuum.exception.HeroClassNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kolyanlock.heroesinvacuum.mapper.HeroClassMapper.HERO_CLASS_MAPPER;

@Service
@RequiredArgsConstructor
public class HeroClassServiceImpl implements HeroClassService {
    private final HeroClassRepository heroClassRepository;

    @Override
    public List<HeroClassDTO> getAllHeroClasses(Sort sort) {
        return heroClassRepository.findAll(sort).stream().map(HERO_CLASS_MAPPER::toHeroClassDTO).collect(Collectors.toList());
    }

    @Override
    public HeroClassDTO getHeroClassById(int id) {
        Optional<HeroClass> optionalHeroClass = heroClassRepository.findById(id);
        if (optionalHeroClass.isEmpty())
            throw new HeroClassNotFoundException(id);
        return HERO_CLASS_MAPPER.toHeroClassDTO(optionalHeroClass.get());
    }

    @Override
    public HeroClassDTO createHeroClass(HeroClassDTO heroClassDTO) {
        String title = heroClassDTO.getTitle();
        if (heroClassRepository.findByTitle(title).isPresent()) {
            throw new HeroClassExistsException(title);
        }
        HeroClass newHeroClass = heroClassRepository.save(HERO_CLASS_MAPPER.toHeroClassEntity(heroClassDTO));
        return HERO_CLASS_MAPPER.toHeroClassDTO(newHeroClass);
    }

    @Override
    public HeroClassDTO updateHeroClass(int id, HeroClassDTO heroClassDTO) {
        Optional<HeroClass> heroClassOptional = heroClassRepository.findById(id);
        if (heroClassOptional.isEmpty())
            throw new HeroClassNotFoundException(id);

        String title = heroClassDTO.getTitle();
        if (heroClassRepository.findByTitle(title).isPresent()) {
            throw new HeroClassExistsException(title);
        }

        HeroClass heroClass = heroClassOptional.get();
        HERO_CLASS_MAPPER.updateHeroClassEntity(heroClassDTO, heroClass);
        return HERO_CLASS_MAPPER.toHeroClassDTO(heroClassRepository.save(heroClass));
    }

    @Override
    public String deleteHeroClassById(int id) {
        try {
            heroClassRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroClassNotFoundException(id);
        }
        return "Hero Class with id " + id + " was deleted.";
    }
}
