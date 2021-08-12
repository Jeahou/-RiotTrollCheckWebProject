package com.riotdata.demo.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String name;

    @Column(length = 500, nullable = false)
    private String accountId;

    private long summonerLevel;

    private String tier;
    private String rank;
    private int point;

    @Builder
    public User(String accountId, String name, long summonerLevel, String tier, String rank, int point){
        this.accountId = accountId;
        this.name = name;
        this.summonerLevel = summonerLevel;
        this.tier = tier;
        this.rank = rank;
        this.point = point;
    }

    public void update(String name, long summonerLevel){
        this.name = name;
        this.summonerLevel = summonerLevel;
    }
}
