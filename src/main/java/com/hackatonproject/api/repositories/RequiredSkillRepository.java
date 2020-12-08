package com.hackatonproject.api.repositories;

import com.hackatonproject.api.models.HeistSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RequiredSkillRepository extends CrudRepository<HeistSkill, Integer> {
    @Query("from HeistSkill where heist_id = ?1")
    List<HeistSkill> findAllByHeistId(Integer heistId);

    @Query("select hs from HeistSkill hs inner join hs.skill s join hs.heist h where s.name = ?2 and hs.levelRequired = ?3 and h.id = ?1 ")
    Optional<HeistSkill> findByHeistAndNameAndLevel(Integer heistId, String name, String level);
}
