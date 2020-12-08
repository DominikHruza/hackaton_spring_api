package com.hackatonproject.api.DTO;

import lombok.Data;

@Data
public class RequiredSkillDTO {
    private String name;
    private String level;
    private Integer members;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequiredSkillDTO)) return false;

        RequiredSkillDTO that = (RequiredSkillDTO) o;

        if (getLevel().equals(that.getLevel()) &&
            getName().equals(that.getName())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() ^ getLevel().hashCode();
    }
}
