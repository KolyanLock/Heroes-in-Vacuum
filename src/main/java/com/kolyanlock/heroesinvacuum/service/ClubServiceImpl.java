package com.kolyanlock.heroesinvacuum.service;

import com.kolyanlock.heroesinvacuum.dao.ClubRepository;
import com.kolyanlock.heroesinvacuum.dto.ClubDTO;
import com.kolyanlock.heroesinvacuum.entity.Club;
import com.kolyanlock.heroesinvacuum.exception.ClubExistsException;
import com.kolyanlock.heroesinvacuum.exception.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kolyanlock.heroesinvacuum.mapper.ClubMapper.CLUB_MAPPER;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;

    @Override
    public List<ClubDTO> getAllClubs(Sort sort) {
        return clubRepository.findAll(sort).stream().map(CLUB_MAPPER::toClubDTO).collect(Collectors.toList());
    }

    @Override
    public ClubDTO getClubById(int id) {
        Optional<Club> clubOptional = clubRepository.findById(id);
        if (clubOptional.isEmpty()) {
            throw new ClubNotFoundException(id);
        }
        return CLUB_MAPPER.toClubDTO(clubOptional.get());
    }

    @Override
    public ClubDTO createClub(ClubDTO clubDTO) {
        String title = clubDTO.getTitle();
        if (clubRepository.findByTitle(title).isPresent()) {
            throw new ClubExistsException(title);
        }
        Club newClub = clubRepository.save(CLUB_MAPPER.toClubEntity(clubDTO));
        return CLUB_MAPPER.toClubDTO(newClub);
    }

    @Override
    public ClubDTO updateClub(int id, ClubDTO clubDTO) {
        Optional<Club> clubOptional = clubRepository.findById(id);
        if (clubOptional.isEmpty()) {
            throw new ClubNotFoundException(id);
        }

        String title = clubDTO.getTitle();
        if (clubRepository.findByTitle(title).isPresent()) {
            throw new ClubExistsException(title);
        }

        Club club = clubOptional.get();
        CLUB_MAPPER.updateClubEntity(clubDTO, club);
        return CLUB_MAPPER.toClubDTO(club);
    }

    @Override
    public String deleteClubById(int id) {
        try {
            clubRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ClubNotFoundException(id);
        }
        return "Club with id " + id + " was deleted.";
    }
}
