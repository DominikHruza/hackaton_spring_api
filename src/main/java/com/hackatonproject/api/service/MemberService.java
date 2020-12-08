package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.models.Skill;

import java.util.List;


public interface MemberService {
     List<MemberDTO> findAll();
     MemberDTO findByName(String name);
     Integer addMember(MemberDTO member);
     MemberDTO updateMainSkill(Integer memberId, Skill skill);
}
