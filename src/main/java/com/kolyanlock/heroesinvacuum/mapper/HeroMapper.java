package com.kolyanlock.heroesinvacuum.mapper;

import com.kolyanlock.heroesinvacuum.dto.HeroDTO;
import com.kolyanlock.heroesinvacuum.entity.Hero;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HeroMapper {
    HeroMapper HERO_MAPPER = Mappers.getMapper(HeroMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alive", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastChange", ignore = true)
    Hero toHeroEntity(HeroDTO heroDTO);

    HeroDTO toHeroDTO(Hero hero);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastChange", ignore = true)
    @Mapping(target = "heroClass", ignore = true)
    @Mapping(target = "clubs", ignore = true)
    @Mapping(target = "alive", ignore = true)
    void updateHeroEntity(HeroDTO heroDTO, @MappingTarget Hero hero);
}
