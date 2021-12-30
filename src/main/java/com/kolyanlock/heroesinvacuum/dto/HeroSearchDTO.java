package com.kolyanlock.heroesinvacuum.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class HeroSearchDTO {

    @Size(max = 30)
    private String name;

    @Size(max = 50)
    private String title;

    private String heroClassDescription;

    List<Integer> clubIdList;
}
