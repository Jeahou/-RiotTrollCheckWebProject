package com.riotdata.demo.domain.riotChamp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, String> {
}
