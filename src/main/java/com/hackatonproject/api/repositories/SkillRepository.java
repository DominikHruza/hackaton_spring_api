package com.hackatonproject.api.repositories;

import com.hackatonproject.api.models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    public Optional<Skill> findByName(String Name);
}
