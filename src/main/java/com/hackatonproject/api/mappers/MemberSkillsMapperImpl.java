package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.MemberSkillDTO;
import com.hackatonproject.api.DTO.MemberSkillsDataDTO;
import com.hackatonproject.api.models.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberSkillsMapperImpl implements MemberSkillsMapper {
    @Override
    public MemberSkillsDataDTO memberSkillToMemberSkillDTO(Member member) {

          MemberSkillsDataDTO memberSkillsDataDTO = new MemberSkillsDataDTO();

          List<MemberSkillDTO> mappedSkills = member.getMemberSkills()
                .stream()
                .map((skill) -> new MemberSkillDTO(skill.getSkill().getName(), skill.getLevel()))
                .collect(Collectors.toList());

          memberSkillsDataDTO.setSkills(mappedSkills);
          memberSkillsDataDTO.setMainSkill(member.getMainSkill().getName());

        return memberSkillsDataDTO;
    }
}
