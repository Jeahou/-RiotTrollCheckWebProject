package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.user.User;
import lombok.Getter;


@Getter
public class UserResponseDto {
    private String accountId;
    private String name;
    private long summonerLevel;
    private String tier;
    private String rank;
    private int point;

    public UserResponseDto(User user) {
        this.accountId = user.getAccountId();
        this.name = user.getName();
        this.summonerLevel = user.getSummonerLevel();
        this.rank = user.getRank();
        this.tier = user.getTier();
        this.point = user.getPoint();
    }

    public User toEntity(){
        return User.builder()
                .summonerLevel(summonerLevel)
                .name(name)
                .accountId(accountId)
                .rank(rank)
                .tier(tier)
                .point(point)
                .build();
    }
}
