package com.kolyanlock.heroesinvacuum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HeroDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Size(max = 50)
    private String title;

    @NotNull
    private HeroClassDTO heroClass;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate lastChange;

    private List<ClubDTO> clubs;
}
