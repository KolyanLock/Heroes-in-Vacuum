package com.kolyanlock.heroesinvacuum.dao;

import com.kolyanlock.heroesinvacuum.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long>,  JpaSpecificationExecutor<Hero> {

    Optional<Hero> findAllByIdAndAliveIsTrue(Long id);

    Optional<Hero> findByName(String name);

    Page<Hero> findAllByHeroClass_Title(String heroClass, Pageable pageable);
}
