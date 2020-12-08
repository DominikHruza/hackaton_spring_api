package com.hackatonproject.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.DTO.MemberSkillsDataDTO;
import com.hackatonproject.api.exceptions.MemberNotFoundException;
import com.hackatonproject.api.exceptions.MemberSkillsDuplicateException;
import com.hackatonproject.api.exceptions.SkillNotInMemberSkillsException;
import com.hackatonproject.api.helpers.Helpers;
import com.hackatonproject.api.mappers.MemberMapper;
import com.hackatonproject.api.mappers.MemberSkillsMapper;
import com.hackatonproject.api.mappers.SkillMapper;
import com.hackatonproject.api.models.Member;
import com.hackatonproject.api.models.Skill;
import com.hackatonproject.api.repositories.MemberRepository;
import com.hackatonproject.api.repositories.MemberSkillsRepository;
import com.hackatonproject.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberSkillsMapper memberSkillsMapper;
    private final SkillMapper skillMapper;
    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final MemberSkillsRepository memberSkillsRepository;
    private final MemberSkillService memberSkillService;

    @Autowired
    public MemberServiceImpl(
            MemberMapper memberMapper,
            MemberSkillsMapper memberSkillsMapper,
            SkillMapper skillsMapper,
            MemberRepository memberRepository,
            SkillRepository skillRepository,
            MemberSkillsRepository memberSkillsRepository,
            MemberSkillService memberSkillService
    ) {
        this.memberMapper = memberMapper;
        this.memberSkillsMapper = memberSkillsMapper;
        this.skillMapper = skillsMapper;
        this.memberRepository = memberRepository;
        this.skillRepository = skillRepository;
        this.memberSkillsRepository = memberSkillsRepository;
        this.memberSkillService = memberSkillService;
    }

    @Override
    public List<MemberDTO> findAll() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(),false)
                .map(memberMapper::memberToMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO findByName(String name) {
        try {
            return memberMapper.memberToMemberDTO(memberRepository.findByName(name));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addMember(MemberDTO member) {
        ObjectMapper objectMapper = new ObjectMapper();

        String name = member.getName();
        Character sex = member.getSex();
        String email = member.getEmail();
        String mainSkill = member.getMemberSkills().getMainSkill();
        String memberStatus = member.getStatus();
        String skills = null;

        if(Helpers.checkForDuplicates(member.getMemberSkills().getSkills())){
            throw new MemberSkillsDuplicateException();
        }

        try {
            skills = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(member.getMemberSkills().getSkills());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int createdId = memberRepository.createMember(name, sex, email, mainSkill, memberStatus, skills);
        return createdId;
    }

    @Override
    public MemberDTO updateMainSkill(Integer memberId, Skill skill) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        //Check if member with passed id exists
        if(!optionalMember.isPresent()) {
            throw new MemberNotFoundException();
        }

        //Check if selected main skill belongs to selected member
        Optional<Skill> optionalMainSkill = skillRepository.findByName(skill.getName());
        if (!optionalMainSkill.isPresent()){
            throw new SkillNotInMemberSkillsException();
        }

        Integer mainSkillId = optionalMainSkill.get().getId();
        skill.setId(mainSkillId);

        Member member = optionalMember.get();
        member.setMainSkill(skill);
        Member updatedMember = memberRepository.save(member);

        //Prepare member DTO
        MemberSkillsDataDTO memberSkillsDataDTO = memberSkillsMapper.memberSkillToMemberSkillDTO(updatedMember);
        MemberDTO memberDTO = memberMapper.memberToMemberDTO(updatedMember);
        memberDTO.setMemberSkills(memberSkillsDataDTO);
        return memberDTO ;
    }
}
