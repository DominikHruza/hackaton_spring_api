package com.hackatonproject.api.DTO;

import lombok.Data;

@Data
public class MemberSkillDTO {
    private String name;
    private String level;

    public MemberSkillDTO() {

    }

    public MemberSkillDTO(String name, String level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberSkillDTO)) return false;

        MemberSkillDTO that = (MemberSkillDTO) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
