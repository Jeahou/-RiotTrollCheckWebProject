package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.user.Matches;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GameIdApiResponseDto {
    private Matches[] matches;
}
