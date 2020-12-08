package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.RequiredSkillDTO;
import com.hackatonproject.api.models.HeistSkill;


public interface RequiredSkillMapper {
    RequiredSkillDTO requiredSkillToRequiredSkillDTO(HeistSkill requiredSkill);
}
