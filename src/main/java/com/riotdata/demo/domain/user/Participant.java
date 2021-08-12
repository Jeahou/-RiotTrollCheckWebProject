package com.riotdata.demo.domain.user;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Participant {
    private int participantId;
    private int teamId;
    private int championId;
    private int spell1Id;
    private int spell2Id;
    private Stats stats;
    private Timeline timeline;
}
