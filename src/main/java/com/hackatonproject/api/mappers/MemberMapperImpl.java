package com.hackatonproject.api.mappers;

import com.hackatonproject.api.DTO.MemberDTO;
import com.hackatonproject.api.models.Member;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {
    @Override
    public MemberDTO memberToMemberDTO(Member member) {
        ModelMapper mapper = new ModelMapper();
        MemberDTO map = mapper.map(member, MemberDTO.class);

        return map;
    }
}
