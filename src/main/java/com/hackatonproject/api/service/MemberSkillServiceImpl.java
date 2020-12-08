package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillDTO;
import com.hackatonproject.api.exceptions.MemberNotFoundException;
import com.hackatonproject.api.exceptions.MemberSkillsDuplicateException;
import com.hackatonproject.api.exceptions.SkillNotInMemberSkillsException;
import com.hackatonproject.api.helpers.Helpers;
import com.hackatonproject.api.mappers.MemberMapper;
import com.hackatonproject.api.models.Member;
import com.hackatonproject.api.models.MemberSkill;
import com.hackatonproject.api.models.Skill;
import com.hackatonproject.api.repositories.MemberRepository;
import com.hackatonproject.api.repositories.MemberSkillsRepository;
import com.hackatonproject.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberSkillServiceImpl implements MemberSkillService {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final MemberSkillsRepository memberSkillsRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public MemberSkillServiceImpl(
            MemberMapper memberMapper,
            MemberRepository memberRepository,
            MemberSkillsRepository memberSkillsRepository,
            SkillRepository skillRepository
    ) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
        this.memberSkillsRepository = memberSkillsRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public MemberDTO updateSkillsList(Integer memberId, List<MemberSkillDTO> skillsUpdateData) {
        //Check if member exists
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(!optionalMember.isPresent()) {
            throw new MemberNotFoundException();
        }
        Member member = optionalMember.get();

        // Check if skill update list contains duplicates
        if(Helpers.checkForDuplicates(skillsUpdateData)) {
            throw new MemberSkillsDuplicateException();
        }

        List<MemberSkill> updatedMemberSkills = skillsUpdateData.stream().map((skillMemberDto) -> {

            Optional<MemberSkill> oldMemberSkill = memberSkillsRepository.findBySkillName(skillMemberDto.getName());
            //Check if member already has selected skill then update only level
            if(!oldMemberSkill.isPresent()){

                //Check if skill exists in db
                Optional<Skill> skillIsInDb = skillRepository.findByName(skillMemberDto.getName());
                if(!skillIsInDb.isPresent()){
                    //if not exists save it to db first then set to member and save member
                    Skill newSkill = skillRepository.save(new Skill(skillMemberDto.getName()));
                    MemberSkill newMemberSkill = new MemberSkill(newSkill, skillMemberDto.getLevel(), member);
                    memberSkillsRepository.save(newMemberSkill);
                    return newMemberSkill;
                }

                //If skill exists in db then just save to member
                memberSkillsRepository.save(new MemberSkill(skillIsInDb.get(), skillMemberDto.getLevel(), member));
                return new MemberSkill(skillIsInDb.get(), skillMemberDto.getLevel(), member);
            }


            oldMemberSkill.get().setLevel(skillMemberDto.getLevel());
            return  oldMemberSkill.get();

        }).collect(Collectors.toList());

       member.setMemberSkills(updatedMemberSkills);
       Member updatedMember =  memberRepository.save(member);

       return memberMapper.memberToMemberDTO(updatedMember);
    }

    @Override
    public void deleteMemberSkill(String skillName, Integer memberId) {

        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()){
            throw new MemberNotFoundException();
        }

        List<MemberSkill> memberSkills = member.get().getMemberSkills();
        Optional<MemberSkill> foundMemberSkill = memberSkills.
             stream()
             .filter(memberSkill ->
                skillName.equals(memberSkill.getSkill().getName()))
             .collect(Collectors.toList()).stream().findFirst();

        if(!foundMemberSkill.isPresent()){
            throw new SkillNotInMemberSkillsException();
        }

        memberSkillsRepository.delete(foundMemberSkill.get());
    }
}
