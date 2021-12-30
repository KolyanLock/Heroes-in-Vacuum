package com.kolyanlock.heroesinvacuum.mapper;

import com.kolyanlock.heroesinvacuum.dto.HeroClassDTO;
import com.kolyanlock.heroesinvacuum.entity.HeroClass;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HeroClassMapper {
    HeroClassMapper HERO_CLASS_MAPPER = Mappers.getMapper( HeroClassMapper.class );

    @Mapping(target = "description", defaultValue = "There is no description yet.")
    HeroClassDTO toHeroClassDTO(HeroClass heroClass);

    @Mapping(target = "id", ignore = true)
    HeroClass toHeroClassEntity(HeroClassDTO heroClassDTO);

    @Mapping(target = "id", ignore = true)
    void updateHeroClassEntity(HeroClassDTO heroClassDTO, @MappingTarget HeroClass heroClass);
}
