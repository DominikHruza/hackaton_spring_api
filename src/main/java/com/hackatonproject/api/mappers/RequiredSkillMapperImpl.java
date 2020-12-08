package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.RequiredSkillDTO;
import com.hackatonproject.api.models.HeistSkill;
import org.springframework.stereotype.Component;

@Component
public class RequiredSkillMapperImpl implements RequiredSkillMapper {
    @Override
    public RequiredSkillDTO requiredSkillToRequiredSkillDTO(HeistSkill requiredSkill) {

        RequiredSkillDTO requiredSkillDTO = new RequiredSkillDTO();
        requiredSkillDTO.setLevel(requiredSkill.getLevelRequired());
        requiredSkillDTO.setMembers(requiredSkill.getMembersRequired());
        requiredSkillDTO.setName(requiredSkill.getSkill().getName());

        return requiredSkillDTO;
    }

}
