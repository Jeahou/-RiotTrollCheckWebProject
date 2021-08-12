package com.riotdata.demo.service;

import com.riotdata.demo.domain.user.Participant;
import com.riotdata.demo.web.dto.GameResultResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculTrolService {
    //kda 계산
    public int calKda(Participant participant){
        int kill = participant.getStats().getKills();
        int death = participant.getStats().getDeaths();
        int assist = participant.getStats().getAssists();
        double kda = (double) (kill+assist) / death;
        if(kda < 1) return 10;
        if(kda >= 1 && kda <2) return 3;
        if(kda >=2 && kda <3) return 1;
        return 0;
    }
    //아이템 멘탈
    public int sameItem(Participant participant){
        ArrayList<Integer> itemList = new ArrayList<>();
        itemList.add(participant.getStats().getItem0());
        itemList.add(participant.getStats().getItem1());
        itemList.add(participant.getStats().getItem2());
        itemList.add(participant.getStats().getItem3());
        itemList.add(participant.getStats().getItem4());
        itemList.add(participant.getStats().getItem5());
        double noItem = 0;
        int sameItem = participant.getStats().getItem0();
        for(int i : itemList){
            noItem += i;
        }
        if(noItem == 0) return 10;
        if( noItem/6 == sameItem) return 10;
        return 0;
    }
    //최근 솔랭 시간
    public String timeToGame(String GameStartTime){
        return "";
    }

}
