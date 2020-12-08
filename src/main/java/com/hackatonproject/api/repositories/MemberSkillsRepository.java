package com.hackatonproject.api.repositories;

import com.hackatonproject.api.models.MemberSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberSkillsRepository extends CrudRepository<MemberSkill, Integer> {
     Optional<MemberSkill> findBySkillName(String name);
     Optional<MemberSkill> findByMemberAndSkill(Integer memberId, String skillName);

}
