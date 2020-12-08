package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillDTO;

import java.util.List;

public interface MemberSkillService {
    MemberDTO updateSkillsList(Integer memberId, List<MemberSkillDTO> skills);
    void deleteMemberSkill(String skillName, Integer memberId);

}
