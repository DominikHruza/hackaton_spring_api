package com.hackatonproject.api.controllers;


import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillsDTO;
import com.hackatonproject.api.exceptions.DuplicateEntryException;
import com.hackatonproject.api.exceptions.DuplicateRequriedSkillException;
import com.hackatonproject.api.exceptions.SkillNotFoundException;
import com.hackatonproject.api.facades.AddNewHeistFacade;
import com.hackatonproject.api.service.HeistService;
import com.hackatonproject.api.service.RequiredSkillsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HeistController {

    AddNewHeistFacade addNewHeistFacade;
    RequiredSkillsService requiredSkillsService;
    HeistService heistService;

    public HeistController(AddNewHeistFacade addNewHeistFacade, RequiredSkillsService requiredSkillsService, HeistService heistService) {
        this.addNewHeistFacade = addNewHeistFacade;
        this.requiredSkillsService = requiredSkillsService;
        this.heistService = heistService;
    }

    @PostMapping(value = "/heist")
    HeistDTO addHeist(@RequestBody HeistDTO heistDTO){

        HeistDTO newHeistDTO = null;

        try {
            newHeistDTO = addNewHeistFacade.addNewHeist(heistDTO);
            return newHeistDTO;
        } catch(Exception e) {
            if(e instanceof DuplicateEntryException){
                throw new DuplicateEntryException("Heist already exist");
            }

            if(e instanceof DuplicateRequriedSkillException){
                throw new DuplicateRequriedSkillException("Check for duplicates in required skills list");
            }

            if(e instanceof SkillNotFoundException){
                throw new SkillNotFoundException("In list required skill does not exist");
            }
        }
        return newHeistDTO;
    }

    @PatchMapping(value = "/heist/{heistId}/skill")
    HeistDTO updateHeistSkills(@RequestBody RequiredSkillsDTO requiredSkillDTOS, @PathVariable("heistId") Integer heistId){

        heistService.updateHeistSkills(heistId, requiredSkillDTOS);

        return null;
    }
}
