package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.riotUser.RiotUser;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Getter
public class UserSumResponseDto implements Serializable {
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;


    public UserSumResponseDto(RiotUser riotUser){

        this.accountId = riotUser.getAccountId();
        this.profileIconId = riotUser.getProfileIconId();
        this.revisionDate = riotUser.getRevisionDate();
        this.name = riotUser.getName();
        this.id = riotUser.getSummonerId();
        this.puuid = riotUser.getPuuid();
        this.summonerLevel = riotUser.getSummonerLevel();
    }

}
