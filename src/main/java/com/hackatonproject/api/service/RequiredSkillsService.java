package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillDTO;
import com.hackatonproject.api.models.Heist;

import java.util.List;

public interface RequiredSkillsService {
    List<RequiredSkillDTO> setRequiredHeistSkills(List<RequiredSkillDTO> requiredSkillDTOList, HeistDTO heistDTO);
    List<RequiredSkillDTO> updateRequiredSkillList(List<RequiredSkillDTO> requiredSkillDTOS, Heist heist);
}
