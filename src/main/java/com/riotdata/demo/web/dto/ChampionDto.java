package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.riotChamp.Champion;
import lombok.Getter;

@Getter
public class ChampionDto {
    private String id;
    private String name;
    private String engName;

    public ChampionDto(Champion champion) {
        this.id = champion.getId();
        this.name = champion.getName();
        this.engName = champion.getEngName();
    }

    public Champion toEntity(){
        return Champion.builder()
                .id(id)
                .name(name)
                .engName(engName)
                .build();
    }
}
