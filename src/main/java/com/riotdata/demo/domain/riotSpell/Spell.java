package com.riotdata.demo.domain.riotSpell;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Spell {
    @Id
    private String kid;
    private String id;
    private String name;
    private String description;


    @Builder
    public Spell(String kid, String id, String name, String description) {
        this.kid = kid;
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
