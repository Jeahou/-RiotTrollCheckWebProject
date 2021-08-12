package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.riotSpell.Spell;
import lombok.Getter;

@Getter
public class SpellDto {
    private String kid;
    private String id;
    private String name;
    private String description;

    public SpellDto(Spell spell) {
        this.kid = spell.getKid();
        this.id = spell.getId();
        this.name = spell.getName();
        this.description = spell.getDescription();
    }

    public Spell toEntity(){
        return Spell.builder()
                .kid(kid)
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
