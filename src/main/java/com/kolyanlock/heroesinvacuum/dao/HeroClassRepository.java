package com.kolyanlock.heroesinvacuum.dao;

import com.kolyanlock.heroesinvacuum.entity.HeroClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroClassRepository extends JpaRepository<HeroClass, Integer> {

    Optional<HeroClass> findByTitle(String title);
}
