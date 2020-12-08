package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.models.Member;

public interface MemberMapper {
    public MemberDTO memberToMemberDTO(Member member);
}
