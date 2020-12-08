package com.hackatonproject.api.DTO;

import lombok.Data;

@Data
public class MemberDTO {
    private Integer id;
    private String name;
    private String email;
    private Character sex;
    private MemberSkillsDataDTO memberSkills;
    private String status;
}
