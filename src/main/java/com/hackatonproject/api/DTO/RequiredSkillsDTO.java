package com.hackatonproject.api.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RequiredSkillsDTO {
    List<RequiredSkillDTO> skills;
}
