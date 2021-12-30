package com.kolyanlock.heroesinvacuum.dao;

import com.kolyanlock.heroesinvacuum.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Integer> {

    List<Club> findAllByTitleIn(List<String> titleList);

    Optional<Club> findByTitle(String title);
}
