package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.user.Participant;
import com.riotdata.demo.domain.user.ParticipantIdentities;
import com.riotdata.demo.domain.user.Teams;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class GameResultApiResponseDto {
    private Participant[] participants;
    private ParticipantIdentities[] participantIdentities;
    private Teams[] teams;
    private long gameCreation;
    private long gameDuration;
}
