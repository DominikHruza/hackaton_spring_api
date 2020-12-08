package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.SkillDTO;
import com.hackatonproject.api.models.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SkillMapperImpl implements SkillMapper {
    @Override
    public Skill skillDtoToSkill(SkillDTO skillDTO) {
        ModelMapper mapper = new ModelMapper();
        Skill mapped = mapper.map(skillDTO, Skill.class);
        return null;
    }

    @Override
    public SkillDTO skillToSkillDTO(Skill skill) {
        ModelMapper mapper = new ModelMapper();
        SkillDTO map = mapper.map(skill, SkillDTO.class);

        return map;
    }


}
