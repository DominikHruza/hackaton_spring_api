package com.hackatonproject.api.DTO;

import lombok.Data;

import java.util.List;

@Data
public class HeistDTO {
    private String name;
    private String location;
    private String startTime;
    private String endTime;
    private List<RequiredSkillDTO> skills;


    public HeistDTO(String name, String location, String startTime, String endTime) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

