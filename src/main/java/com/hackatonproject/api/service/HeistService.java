package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillsDTO;

public interface HeistService {
    HeistDTO addHeist(HeistDTO heistDTO);
    HeistDTO updateHeistSkills(Integer heistId, RequiredSkillsDTO skillsDTO);
}
