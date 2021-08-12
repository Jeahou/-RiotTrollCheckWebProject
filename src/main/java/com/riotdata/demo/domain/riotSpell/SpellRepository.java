package com.riotdata.demo.domain.riotSpell;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell, String> {
}
