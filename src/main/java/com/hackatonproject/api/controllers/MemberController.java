package com.hackatonproject.api.controllers;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillsDataDTO;
import com.hackatonproject.api.exceptions.SkillNotInMemberSkillsException;
import com.hackatonproject.api.exceptions.DuplicateEntryException;
import com.hackatonproject.api.exceptions.MemberNotFoundException;
import com.hackatonproject.api.exceptions.MemberSkillsDuplicateException;
import com.hackatonproject.api.facades.UpdateSkillsServiceFacade;
import com.hackatonproject.api.service.MemberService;
import com.hackatonproject.api.service.MemberSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MemberSkillService memberSkillService;
    private final UpdateSkillsServiceFacade updateSkillsServiceFacade;

    @Autowired
    public MemberController(
            MemberService memberService,
            MemberSkillService memberSkillService,
            UpdateSkillsServiceFacade updateSkillsServiceFacade) {
        this.memberService = memberService;
        this.memberSkillService = memberSkillService;
        this.updateSkillsServiceFacade = updateSkillsServiceFacade;
    }


    @PostMapping(value = "/member/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO addMember(@RequestBody MemberDTO member) {

        try {
            int createdId = memberService.addMember(member);
            member.setId(createdId);

        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new DuplicateEntryException("Member with email " + member.getEmail() + " already exists.");
            } else if (e instanceof MemberSkillsDuplicateException) {
                throw new MemberSkillsDuplicateException("Check for duplicates in member skill list");
            }
        }

        return member;
    }

    @PutMapping(value = "member/{id}/skills")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MemberDTO updateSkills(@PathVariable("id") Integer id, @RequestBody MemberSkillsDataDTO skillsData) {
        MemberDTO memberDTO = new MemberDTO();
        try {
            memberDTO = updateSkillsServiceFacade.updateSkillsSetMainSkill( id, skillsData);
        } catch (Exception e){

            if(e instanceof MemberNotFoundException){
                throw new MemberNotFoundException("Member with id " + id + " not found");
            }

            if(e instanceof MemberSkillsDuplicateException){
                throw new MemberSkillsDuplicateException("Check for duplicates in member skill list");
            }

            if(e instanceof SkillNotInMemberSkillsException){
                throw new SkillNotInMemberSkillsException("Selected main skill does not belong to member");
            }
        }

        return memberDTO;
    }

    @DeleteMapping(value = "member/{id}/skills/{skill_name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteMemberSkill(@PathVariable("id") Integer memberId, @PathVariable("skill_name") String skillName){
        try {
            memberSkillService.deleteMemberSkill(skillName, memberId);
        } catch (Exception e) {
            if(e instanceof MemberNotFoundException){
                throw new MemberNotFoundException("Member with id " + memberId + " not found");
            }

            if(e instanceof SkillNotInMemberSkillsException){
                throw new SkillNotInMemberSkillsException("Selected skill does not belong to member");
            }

        }
    }
}
