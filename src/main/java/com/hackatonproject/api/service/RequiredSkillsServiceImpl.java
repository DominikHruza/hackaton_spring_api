package com.hackatonproject.api.service;

import com.hackatonproject.api.DTO.HeistDTO;
import com.hackatonproject.api.DTO.RequiredSkillDTO;
import com.hackatonproject.api.exceptions.DuplicateRequriedSkillException;
import com.hackatonproject.api.exceptions.HeistNotFoundException;
import com.hackatonproject.api.exceptions.SkillNotFoundException;
import com.hackatonproject.api.helpers.Helpers;
import com.hackatonproject.api.mappers.RequiredSkillMapper;
import com.hackatonproject.api.models.Heist;
import com.hackatonproject.api.models.HeistSkill;
import com.hackatonproject.api.models.Skill;
import com.hackatonproject.api.repositories.HeistRepository;
import com.hackatonproject.api.repositories.RequiredSkillRepository;
import com.hackatonproject.api.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RequiredSkillsServiceImpl implements RequiredSkillsService {
    SkillRepository skillRepository;
    RequiredSkillRepository requiredSkillRepository;
    HeistRepository heistRepository;
    RequiredSkillMapper requiredSkillMapper;

    public RequiredSkillsServiceImpl(
            SkillRepository skillRepository,
            RequiredSkillRepository requiredSkillRepository,
            HeistRepository heistRepository,
            RequiredSkillMapper requiredSkillMapper
    ) {
        this.skillRepository = skillRepository;
        this.requiredSkillRepository = requiredSkillRepository;
        this.heistRepository = heistRepository;
        this.requiredSkillMapper = requiredSkillMapper;
    }

    @Override
    public List<RequiredSkillDTO> setRequiredHeistSkills(List<RequiredSkillDTO> requiredSkillDTOList, HeistDTO heistDTO){

        if(Helpers.checkForDuplicates(requiredSkillDTOList)){
            throw new DuplicateRequriedSkillException();
        };

        List<HeistSkill> heistSkills = requiredSkillDTOList
                .stream()
                .map(requiredSkillDTO -> {
                    Optional<Skill> requiredSkill = skillRepository.findByName(requiredSkillDTO.getName());

                    if (!requiredSkill.isPresent()){
                        throw new SkillNotFoundException();
                    }

                    HeistSkill heistSkill = new HeistSkill();
                    heistSkill.setSkill(requiredSkill.get());
                    heistSkill.setMembersRequired(requiredSkillDTO.getMembers());
                    heistSkill.setLevelRequired(requiredSkillDTO.getLevel());

                    Optional<Heist> heistOptional = heistRepository.findByName(heistDTO.getName());
                    if(!heistOptional.isPresent()){
                        throw new HeistNotFoundException();
                    }

                    heistSkill.setHeist(heistOptional.get());
                    return heistSkill;
                }).collect(Collectors.toList());

        Iterable<HeistSkill> savedRequiredSkills = requiredSkillRepository.saveAll(heistSkills);

        List<RequiredSkillDTO> requiredSkillDTOS = StreamSupport
                .stream(savedRequiredSkills.spliterator(), false)
                .map(requiredSkill -> requiredSkillMapper.requiredSkillToRequiredSkillDTO(requiredSkill))
                .collect(Collectors.toList());

       return requiredSkillDTOS;
    }

    @Override
    public List<RequiredSkillDTO> updateRequiredSkillList(List<RequiredSkillDTO> requiredSkillDTOS, Heist heist) {

        List<HeistSkill> currentSkills = heist.getHeistSkills();

        List<HeistSkill> updatedSkillList = requiredSkillDTOS
                .stream()
                .map(requiredSkillDTO -> {
                    Optional<HeistSkill> heistSkill = requiredSkillRepository.findByHeistAndNameAndLevel( heist.getId(),requiredSkillDTO.getName(), requiredSkillDTO.getLevel());

                  HeistSkill nesto = null;
                  return nesto;

                }).collect(Collectors.toList());

        return null;
    }


}
