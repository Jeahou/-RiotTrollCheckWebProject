package com.riotdata.demo.web;

import com.riotdata.demo.api.ChampionApiClient;
import com.riotdata.demo.api.RiotApiClient;
import com.riotdata.demo.api.SpellApiClient;
import com.riotdata.demo.domain.riotChamp.Champion;
import com.riotdata.demo.domain.riotSpell.Spell;
import com.riotdata.demo.domain.user.Matches;
import com.riotdata.demo.domain.user.Participant;
import com.riotdata.demo.domain.user.ParticipantIdentities;
import com.riotdata.demo.domain.user.Teams;
import com.riotdata.demo.service.CalculTrolService;
import com.riotdata.demo.service.UserService;
import com.riotdata.demo.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UserService userService;
    private final RiotApiClient riotApiClient;
    private final SpellApiClient spellApiClient;
    private final ChampionApiClient championApiClient;
    private final CalculTrolService calculTrolService;

    @GetMapping("/")
    public String index(){
        if(!userService.findChamp("1")){
            System.out.println("오류 안발생");
            JSONObject champJsonObject = championApiClient.championFind();
            JSONObject spellJsonObject = spellApiClient.spellFind();
            champJsonObject.keySet().forEach(key ->{
                JSONObject tmp = (JSONObject) champJsonObject.get(key);
                Champion champion = new Champion((String) tmp.get("key"), (String) tmp.get("name"), (String) tmp.get("id"));
                ChampionDto championDto = new ChampionDto(champion);
                userService.saveChamp(championDto);
            });
            spellJsonObject.keySet().forEach(key ->{
                JSONObject tmp = (JSONObject) spellJsonObject.get(key);
                Spell spell =  new Spell((String)tmp.get("key"), (String)tmp.get("id"), (String)tmp.get("name"), (String)tmp.get("description"));
                SpellDto spellDto =  new SpellDto(spell);
                userService.saveSpell(spellDto);
            });
        }else{
            System.out.println("아닙니다");
        }
        return "index";
    }
// 여기서 모델 만들고 해결
    @GetMapping("/summoner/{name}")
    public String result(@PathVariable String name, Model model){
        model.addAttribute("user" , userService.findByName(name));
        Mono<GameIdApiResponseDto> test = riotApiClient.findGameId(userService.findByName(name).getAccountId());
        GameIdApiResponseDto test2 = test.block();
        List<GameResultResponseDto> list = new ArrayList<>();
        double winRating = 0;
        int itemTotal = 0;
        int kdaTotal = 0;
        for(int matchIdx=0; matchIdx<20; matchIdx++){
            Matches matches = test2.getMatches()[matchIdx];
            Mono<GameResultApiResponseDto> testt = riotApiClient.findGanmeResult(matches.getGameId());
            GameResultApiResponseDto testt2 = testt.block();
            long gameStartTime = testt2.getGameCreation();
            String msg = riotApiClient.calTime(gameStartTime);
            int nameId = -1;
            for(int idx = 0; idx<10; idx++){
                ParticipantIdentities participantIdentities = testt2.getParticipantIdentities()[idx];
                if(participantIdentities.getPlayer().getSummonerName().equals(name)){
                    nameId = participantIdentities.getParticipantId();
                    break;
                }
            }
            Participant participant = testt2.getParticipants()[nameId-1];
            String win = "";
            for(int i=0; i<2; i++){
                Teams teams = testt2.getTeams()[i];
                if(teams.getTeamId() == participant.getTeamId()) win = teams.getWin();
            }
            if(win.equals("Win")) winRating += 1;
            String spell1 = userService.findSpellName(Integer.toString(participant.getSpell1Id()));
            String spell2 = userService.findSpellName(Integer.toString(participant.getSpell2Id()));
            String spell1Url = "https://ddragon.leagueoflegends.com/cdn/11.11.1/img/spell/"+spell1+".png";
            String spell2Url = "https://ddragon.leagueoflegends.com/cdn/11.11.1/img/spell/"+spell2+".png";
            String champ = userService.findChampName(Integer.toString(participant.getChampionId()));
            String Url = "http://ddragon.leagueoflegends.com/cdn/11.12.1/img/champion/" + champ + ".png";
            GameResultResponseDto gameResultResponseDto = new GameResultResponseDto(participant, win,  champ, Url, spell1Url, spell2Url, msg);
            list.add(gameResultResponseDto);
            itemTotal += calculTrolService.sameItem(participant);
            kdaTotal += calculTrolService.calKda(participant);
        }
        winRating /=20; winRating*=100;
        String itemTroll = "정상";
        String kdaTroll = "잘함";
        if(itemTotal >= 10) {
            itemTotal /= 10;
            itemTroll = "최근 20게임 중 "+Integer.toString(itemTotal)+"번 아이템을 개떡같이 한적있습니다. 멘탈이 많이 약함";
        }
        if(kdaTotal >= 60) {
            kdaTroll = "전반적으로 못함";
        }else if(kdaTotal >= 50){
            kdaTroll = "못하는 판이 많음";
        }else if(kdaTotal >= 40){
            kdaTroll = "주사위형 게이머";
        }
        model.addAttribute("itemTroll", itemTroll);
        model.addAttribute("kdaTroll", kdaTroll);
        model.addAttribute("winRating", winRating);
        model.addAttribute("gameResult" , list);
        return "result";
    }
}
