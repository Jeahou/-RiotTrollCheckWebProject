package com.riotdata.demo.web;

import com.riotdata.demo.api.RiotApiClient;
import com.riotdata.demo.domain.user.*;
import com.riotdata.demo.service.UserService;
import com.riotdata.demo.web.dto.UserLeagueResponseDto;
import com.riotdata.demo.web.dto.UserSumResponseDto;
import com.riotdata.demo.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class RiotApiController {

    private final RiotApiClient riotApiClient;
    private final UserService userService;

    @GetMapping("/api/v1/riot/{keyword}")
    // 수정사항 데이터 베이스에 잇으면 수정하고 콜
    public UserResponseDto TestWebClient(@PathVariable String keyword) {
        UserResponseDto userResponseDto = riotApiClient.findUser(keyword);
        userService.save(userResponseDto);
        return userResponseDto;
    }
}
