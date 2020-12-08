package com.hackatonproject.api.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MemberSkillsDataDTO {
    private List<MemberSkillDTO> skills;
    private String mainSkill;
}
