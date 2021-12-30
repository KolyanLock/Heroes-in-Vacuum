package com.kolyanlock.heroesinvacuum.mapper;

import com.kolyanlock.heroesinvacuum.dto.ClubDTO;
import com.kolyanlock.heroesinvacuum.entity.Club;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClubMapper {
    ClubMapper CLUB_MAPPER = Mappers.getMapper( ClubMapper.class );

    @Mapping(target = "description", defaultValue = "There is no description yet.")
    ClubDTO toClubDTO(Club club);

    @Mapping(target = "id", ignore = true)
    Club toClubEntity(ClubDTO clubDTO);

    @Mapping(target = "id", ignore = true)
    void updateClubEntity(ClubDTO clubDTO, @MappingTarget Club club);
}
