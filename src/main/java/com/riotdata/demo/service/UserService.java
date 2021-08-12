package com.riotdata.demo.service;

import com.riotdata.demo.domain.riotChamp.Champion;
import com.riotdata.demo.domain.riotChamp.ChampionRepository;
import com.riotdata.demo.domain.riotSpell.Spell;
import com.riotdata.demo.domain.riotSpell.SpellRepository;
import com.riotdata.demo.domain.user.User;
import com.riotdata.demo.domain.user.UserRepository;
import com.riotdata.demo.web.dto.ChampionDto;
import com.riotdata.demo.web.dto.SpellDto;
import com.riotdata.demo.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ChampionRepository championRepository;
    private final SpellRepository spellRepository;
    //유저정보
    @Transactional
    public UserResponseDto findByName(String name){
        User user = userRepository.findById(name).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + name));
        return new UserResponseDto(user);
    }

    @Transactional
    public String save(UserResponseDto dto){
        return userRepository.save(dto.toEntity()).getAccountId();
    }
    // 챔피언
    @Transactional
    public void saveChamp(ChampionDto dto) {
        championRepository.save(dto.toEntity());
    }
    @Transactional(readOnly = true)
    public boolean findChamp(String id){
        return championRepository.existsById(id);
    }
    @Transactional(readOnly = true)
    public String findChampName(String key){
        Champion champion = championRepository.findById(key).orElseThrow(() -> new IllegalArgumentException("해당 챔피언이 없습니다. "+ key ));
        return champion.getEngName();
    }
    //스펠
    @Transactional
    public void saveSpell(SpellDto dto){
        spellRepository.save(dto.toEntity());
    }
    @Transactional(readOnly = true)
    public String findSpellName(String key){
        Spell spell = spellRepository.findById(key).orElseThrow(() -> new IllegalArgumentException("해당 스펠이 없습니다. " + key));
        return spell.getId();

    }
}
