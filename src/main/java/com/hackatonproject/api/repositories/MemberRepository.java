package com.hackatonproject.api.repositories;

import com.hackatonproject.api.models.Member;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Member findByName(String term);


    @Procedure(procedureName = "createMember", outputParameterName = "createdId")
    Integer createMember(String memberName, Character sex, String email, String mainSkill, String memberStatus, String skills);


}
