package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dao.ClubRepository;
import com.kolyanlock.heroesinvacuum.dao.HeroClassRepository;
import com.kolyanlock.heroesinvacuum.dao.HeroRepository;
import com.kolyanlock.heroesinvacuum.dto.ClubDTO;
import com.kolyanlock.heroesinvacuum.dto.HeroDTO;
import com.kolyanlock.heroesinvacuum.dto.HeroSearchDTO;
import com.kolyanlock.heroesinvacuum.entity.Club;
import com.kolyanlock.heroesinvacuum.entity.Hero;
import com.kolyanlock.heroesinvacuum.entity.HeroClass;
import com.kolyanlock.heroesinvacuum.exception.ClubNotFoundException;
import com.kolyanlock.heroesinvacuum.exception.HeroClassNotFoundException;
import com.kolyanlock.heroesinvacuum.exception.HeroExistsException;
import com.kolyanlock.heroesinvacuum.exception.HeroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kolyanlock.heroesinvacuum.dao.specifications.HeroSpecifications.*;
import static com.kolyanlock.heroesinvacuum.mapper.HeroMapper.HERO_MAPPER;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final HeroClassRepository heroClassRepository;
    private final ClubRepository clubRepository;

    @Override
    public Page<HeroDTO> getAllHeroes(Pageable pageable) {
        return heroRepository.findAll(where(isAlive()), pageable).map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> searchHero(Pageable pageable, HeroSearchDTO heroSearchDTO) {
        String name = heroSearchDTO.getName();
        String title = heroSearchDTO.getTitle();
        List<Integer> clubIdList = heroSearchDTO.getClubIdList();
        String likeHeroClassDescription = heroSearchDTO.getHeroClassDescription();
        return heroRepository.findAll(where(isAlive()
                        .and(likeName(name))
                        .and(likeTitle(title))
                        .and(likeHeroClassDescription(likeHeroClassDescription))
                        .and(inClubs(clubIdList))), pageable)
                .map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public HeroDTO getHeroById(long id) {
        Optional<Hero> heroOptional = heroRepository.findAllByIdAndAliveIsTrue(id);
        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException(id);
        }
        return HERO_MAPPER.toHeroDTO(heroOptional.get());
    }

    @Override
    public HeroDTO createNewHero(HeroDTO heroDTO) {
        Hero newHero = HERO_MAPPER.toHeroEntity(heroDTO);
        String name = heroDTO.getName();
        if (heroRepository.findByName(name).isPresent()) {
            throw new HeroExistsException(name);
        }
        String heroClassTitle = heroDTO.getHeroClass().getTitle();
        Optional<HeroClass> heroClassOptional = heroClassRepository.findByTitle(heroClassTitle);
        if (heroClassOptional.isEmpty()) {
            throw new HeroClassNotFoundException(heroClassTitle);
        }

        newHero.setHeroClass(heroClassOptional.get());

        List<ClubDTO> clubDTOList = heroDTO.getClubs();
        if (clubDTOList != null && !clubDTOList.isEmpty()) {
            List<String> clubTitleList = clubDTOList.stream().map(ClubDTO::getTitle).collect(Collectors.toList());
            List<Club> clubList = clubRepository.findAllByTitleIn(clubTitleList);
            if (clubList.isEmpty()) {
                throw new ClubNotFoundException(clubTitleList);
            }
            newHero.setClubs(clubList);
        }

        return HERO_MAPPER.toHeroDTO(heroRepository.save(newHero));
    }

    @Override
    public HeroDTO updateHero(long id, HeroDTO heroDTO) {
        Optional<Hero> heroOptional = heroRepository.findAllByIdAndAliveIsTrue(id);
        if (heroOptional.isEmpty()) {
            throw new HeroNotFoundException(id);
        }

        String name = heroDTO.getName();
        if (heroRepository.findByName(name).isPresent()) {
            throw new HeroExistsException(name);
        }

        Hero hero = heroOptional.get();

        String newHeroClassTitle = heroDTO.getHeroClass().getTitle();
        String oldHeroClassTitle = hero.getHeroClass().getTitle();
        if (!newHeroClassTitle.equals(oldHeroClassTitle)) {
            Optional<HeroClass> heroClassOptional = heroClassRepository.findByTitle(newHeroClassTitle);
            if (heroClassOptional.isEmpty()) {
                throw new HeroClassNotFoundException(newHeroClassTitle);
            }
            HeroClass heroClass = heroClassOptional.get();
            hero.setHeroClass(heroClass);
        }

        List<ClubDTO> clubDTOList = heroDTO.getClubs();
        List<String> newClubTitleList = clubDTOList != null ?
                clubDTOList.stream().map(ClubDTO::getTitle).collect(Collectors.toList())
                : null;
        List<Club> clubList = hero.getClubs();
        List<String> oldClubTitleList = clubList != null ?
                clubList.stream().map(Club::getTitle).collect(Collectors.toList())
                : null;
        if (!Objects.equals(newClubTitleList, oldClubTitleList)) {
            clubList = clubRepository.findAllByTitleIn(newClubTitleList);
            if (clubList.isEmpty()) {
                throw new ClubNotFoundException(newClubTitleList);
            }
            hero.setClubs(clubList);
        }

        HERO_MAPPER.updateHeroEntity(heroDTO, hero);

        return HERO_MAPPER.toHeroDTO(heroRepository.save(hero));
    }

    @Override
    public String deleteHero(long id) {
        Optional<Hero> optionalHero = heroRepository.findAllByIdAndAliveIsTrue(id);
        if (optionalHero.isEmpty())
            throw new HeroNotFoundException(id);
        Hero hero = optionalHero.get();
        hero.setAlive(false);
        heroRepository.save(hero);
        return "HEro with id = " + hero.getId() + " was dead!";
    }

    @Override
    public Page<HeroDTO> findAllByHeroClassTitle(String heroClass, Pageable pageable) {
        return heroRepository.findAllByHeroClass_Title(heroClass, pageable).map(HERO_MAPPER::toHeroDTO);
    }

    @Override
    public Page<HeroDTO> findAllByClub(String club, Pageable pageable) {
        return heroRepository.findAll(where(inClub(club)), pageable).map(HERO_MAPPER::toHeroDTO);
    }
}
