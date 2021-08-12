package com.riotdata.demo.domain.user;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ParticipantIdentities {
    private int participantId;
    private Player player;
}
