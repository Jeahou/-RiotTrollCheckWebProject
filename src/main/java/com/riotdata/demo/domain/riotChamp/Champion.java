package com.riotdata.demo.domain.riotChamp;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Champion {
    @Id
    private String id;
    private String name;
    private String engName;

    @Builder
    public Champion(String id, String name, String engName){
        this.id = id;
        this.name = name;
        this.engName = engName;
    }
}
