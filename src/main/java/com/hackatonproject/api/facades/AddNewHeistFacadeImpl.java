package com.hackatonproject.api.facades;

import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillDTO;
import com.hackatonproject.api.service.HeistService;
import com.hackatonproject.api.service.RequiredSkillsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddNewHeistFacadeImpl implements AddNewHeistFacade {
    RequiredSkillsService requiredSkillsService;
    HeistService heistService;


    public AddNewHeistFacadeImpl(RequiredSkillsService requiredSkillsService, HeistService heistService) {
        this.requiredSkillsService = requiredSkillsService;
        this.heistService = heistService;
    }

    @Override
    public HeistDTO addNewHeist(HeistDTO heistDTO) {

        HeistDTO savedHeistDTO = heistService.addHeist(heistDTO);
        List<RequiredSkillDTO> requiredSkillDTOS = requiredSkillsService
                .setRequiredHeistSkills(heistDTO.getSkills(), savedHeistDTO);

        savedHeistDTO.setSkills(requiredSkillDTOS);
        return savedHeistDTO;
    }
}

