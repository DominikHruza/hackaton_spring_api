package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillsDTO;
import com.hackatonproject.api.exceptions.DuplicateEntryException;
import com.hackatonproject.api.exceptions.HeistNotFoundException;
import com.hackatonproject.api.models.Heist;
import com.hackatonproject.api.repositories.HeistRepository;
import com.hackatonproject.api.repositories.RequiredSkillRepository;
import com.hackatonproject.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeistServiceImpl implements HeistService {
    HeistRepository heistRepository;
    SkillRepository skillRepository;
    RequiredSkillRepository requiredSkillRepository;
    RequiredSkillsService requiredSkillsService;

    @Autowired
    public HeistServiceImpl(SkillRepository skillRepository, HeistRepository heistRepository, RequiredSkillRepository requiredSkillRepository,RequiredSkillsService requiredSkillsService) {
        this.heistRepository = heistRepository;
        this.skillRepository = skillRepository;
        this.requiredSkillRepository = requiredSkillRepository;
        this.requiredSkillsService = requiredSkillsService;
    }

    @Override
    public HeistDTO addHeist(HeistDTO heistDTO){
        Heist newHeist = new Heist(
                heistDTO.getName(),
                heistDTO.getLocation()
        );

        HeistDTO newHeistDTO = null;

        try {
             Heist savedHeist = heistRepository.save(newHeist);
             newHeistDTO =  new HeistDTO(
                     savedHeist.getName(),
                     savedHeist.getLocation(),
                     savedHeist.getStartTime(),
                     savedHeist.getEndTime()
             );
            return newHeistDTO;

        } catch (Exception e){
            if(e instanceof DataIntegrityViolationException){
                throw new DuplicateEntryException();
            }
        }

        return newHeistDTO;
    }

    @Override
    public HeistDTO updateHeistSkills(Integer heistId, RequiredSkillsDTO skillsDTO) {
       Optional<Heist> foundHeist = heistRepository.findById(heistId);

       requiredSkillsService.updateRequiredSkillList(skillsDTO.getSkills(), foundHeist.get());
       if(!foundHeist.isPresent()){
           throw new HeistNotFoundException();
       }

       return null;
    }


}
