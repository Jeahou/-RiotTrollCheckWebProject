package com.riotdata.demo.domain.user;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Matches {
    private long gameId;
    private String role;
    private int season;
    private String platformId;
    private int champion;
    private int queue;
    private String lane;
    private long timestamp;
}
