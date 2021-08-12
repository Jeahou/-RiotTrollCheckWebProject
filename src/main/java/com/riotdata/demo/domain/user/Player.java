package com.riotdata.demo.domain.user;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Player {
    private String platformId;
    private String summonerName;
    private String accountId;
}
