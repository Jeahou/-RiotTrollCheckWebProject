package com.riotdata.demo.web.dto;

import com.riotdata.demo.domain.user.Participant;
import com.riotdata.demo.domain.user.Teams;
import lombok.Getter;

@Getter
public class GameResultResponseDto {
    private String itemUrl = "http://ddragon.leagueoflegends.com/cdn/11.13.1/img/item/";
    private String championName;
    private String championNameUrl;
    private int champoinId;
    private String spell1Name;
    private String spell2Name;
    private String spell1NameUrl;
    private String spell2NameUrl;
    private int spell1Id;
    private int spell2Id;
    private int kills;
    private int deaths;
    private int assists;
    private String lane;
    private String win;
    private String gameStartTime;
    private String durationTime;
    private String item0Url;
    private String item1Url;
    private String item2Url;
    private String item3Url;
    private String item4Url;
    private String item5Url;
    private String item6Url;

    public GameResultResponseDto(Participant participant, String win, String championName, String Url, String spell1NameUrl, String spell2NameUrl, String gameStartTime) {
        this.gameStartTime = gameStartTime;
        this.championName = championName;
        this.championNameUrl = Url;
        this.champoinId = participant.getChampionId();
        this.spell1Id = participant.getSpell1Id();
        this.spell2Id = participant.getSpell2Id();
        this.kills = participant.getStats().getKills();
        this.deaths = participant.getStats().getDeaths();
        this.assists = participant.getStats().getAssists();
        this.lane = participant.getTimeline().getLane();
        this.win = win;
        this.spell1NameUrl = spell1NameUrl;
        this.spell2NameUrl = spell2NameUrl;
        this.item0Url = this.itemUrl+Integer.toString(participant.getStats().getItem0()) + ".png";
        this.item1Url = this.itemUrl+Integer.toString(participant.getStats().getItem1()) + ".png";
        this.item2Url = this.itemUrl+Integer.toString(participant.getStats().getItem2()) + ".png";
        this.item3Url = this.itemUrl+Integer.toString(participant.getStats().getItem3()) + ".png";
        this.item4Url = this.itemUrl+Integer.toString(participant.getStats().getItem4()) + ".png";
        this.item5Url = this.itemUrl+Integer.toString(participant.getStats().getItem5()) + ".png";
        this.item6Url = this.itemUrl+Integer.toString(participant.getStats().getItem6()) + ".png";
    }

}
