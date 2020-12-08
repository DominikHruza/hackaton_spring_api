package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.MemberSkillsDataDTO;
import com.hackatonproject.api.models.Member;

public interface MemberSkillsMapper {
    MemberSkillsDataDTO memberSkillToMemberSkillDTO(Member member);
}
