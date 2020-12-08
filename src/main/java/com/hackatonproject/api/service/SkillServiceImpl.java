package com.hackatonproject.api.service;

import com.hackatonproject.api.mappers.SkillMapper;
import com.hackatonproject.api.repositories.SkillRepository;
import org.springframework.stereotype.Service;


@Service
public class SkillServiceImpl implements SkillService {

    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillMapper skillMapper, SkillRepository skillRepository) {
        this.skillMapper = skillMapper;
        this.skillRepository = skillRepository;
    }

//    @Override
//    public SkillDTO findByName(String name) {
//        return skillMapper.skillToSkillDTO(skillRepository.findByName(name));
//    }
}
