package com.riotdata.demo.domain.riotUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class RiotUser {

    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String summonerId;
    private String puuid;
    private Long summonerLevel;


}

