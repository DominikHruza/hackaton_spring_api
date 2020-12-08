package com.hackatonproject.api.facades;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillDTO;
import com.hackatonproject.api.DTO.MemberSkillsDataDTO;
import com.hackatonproject.api.models.Skill;
import com.hackatonproject.api.service.MemberService;
import com.hackatonproject.api.service.MemberSkillService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSkillsServiceFacadeImpl implements UpdateSkillsServiceFacade {
    private final MemberSkillService memberSkillService;
    private  final MemberService memberService;

    public UpdateSkillsServiceFacadeImpl(
            MemberSkillService memberSkillService,
            MemberService memberService
    ) {
        this.memberSkillService = memberSkillService;
        this.memberService = memberService;
    }

    @Override
    public MemberDTO updateSkillsSetMainSkill(
            Integer memberId,
            MemberSkillsDataDTO skillsData)
    {
        //Prepare data (skills, main skill)
        List<MemberSkillDTO> skillsUpdateList = skillsData.getSkills();
        Skill mainSkill  = new Skill(skillsData.getMainSkill());

        //Execute services
        memberSkillService.updateSkillsList(memberId, skillsUpdateList);
        return memberService.updateMainSkill(memberId, mainSkill);
    }
}
