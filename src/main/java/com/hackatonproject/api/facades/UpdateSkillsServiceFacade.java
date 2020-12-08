package com.hackatonproject.api.facades;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillsDataDTO;

public interface UpdateSkillsServiceFacade {

   MemberDTO updateSkillsSetMainSkill(Integer memberId, MemberSkillsDataDTO skillsData);
}
