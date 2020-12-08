package com.hackatonproject.api.mappers;


import com.hackatonproject.api.DTO.SkillDTO;
import com.hackatonproject.api.models.Skill;

public interface SkillMapper {
    public SkillDTO skillToSkillDTO(Skill skill);
    public Skill skillDtoToSkill(SkillDTO skillDTO);
}
