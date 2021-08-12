package com.riotdata.demo.api;

import com.riotdata.demo.config.ApiKey;
import com.riotdata.demo.domain.user.User;
import com.riotdata.demo.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RiotApiClient {
    @Autowired
    private ApiKey apiKey;
    private final WebClient webClient = WebClient.create();
    private final String riotSummonerUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    private final String riotGameIdUrl = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/";
    private final String riotGameResultUrl  ="https://kr.api.riotgames.com/lol/match/v4/matches/";
    private final String riotTierUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";

    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;

    public UserResponseDto findUser(String name){
        System.out.println("낭영");
        UserSumResponseDto userSumResponseDto =  webClient
                .method(HttpMethod.GET)
                .uri(riotSummonerUrl + name)
                .header("X-Riot-Token",  apiKey.getKey())
                .retrieve()
                .bodyToMono(UserSumResponseDto.class).block();

        JSONArray jsonArray = webClient
                .method(HttpMethod.GET)
                .uri(riotTierUrl + userSumResponseDto.getId())
                .header("X-Riot-Token",  apiKey.getKey())
                .retrieve()
                .bodyToMono(JSONArray.class).block();

        JSONObject jsonObject = new JSONObject((Map) jsonArray.get(0));
        System.out.println(jsonObject);


        System.out.println(jsonObject.get("tier"));
        User user = new User(userSumResponseDto.getAccountId(), userSumResponseDto.getName(), userSumResponseDto.getSummonerLevel(),
                (String) jsonObject.get("tier"), (String) jsonObject.get("rank"), (int) jsonObject.get("leaguePoints"));
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    public Mono<GameIdApiResponseDto> findGameId(String accountId){
        System.out.println("영영");

        return webClient
                .method(HttpMethod.GET)
                .uri(riotGameIdUrl + accountId + "?endIndex=20")
                .header("X-Riot-Token",  apiKey.getKey())
                .retrieve()
                .bodyToMono(GameIdApiResponseDto.class);
    }

    public Mono<GameResultApiResponseDto> findGanmeResult(long gameId){
        return  webClient
                .method(HttpMethod.GET)
                .uri(riotGameResultUrl + gameId)
                .header("X-Riot-Token",  apiKey.getKey())
                .retrieve()
                .bodyToMono(GameResultApiResponseDto.class);
    }

    public String calTime(long time){
        long currentTime = System.currentTimeMillis();
        long diffTime = (currentTime - time)/1000;
        String msg = null;
        if (diffTime < SEC){
            msg = diffTime + "초 전";
        } else if ((diffTime /= SEC) < MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= MIN) < HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= HOUR) < DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= DAY) < MONTH) {
            msg = (diffTime) + "개월 전";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy");
            String curYear = sdf.format(currentTime);
            String passYear = sdf.format(time);
            int diffYear = Integer.parseInt(curYear) - Integer.parseInt(passYear);
            msg = diffYear + "년 전";
        }
        return msg;
    }

}
