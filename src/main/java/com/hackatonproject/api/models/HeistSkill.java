package com.hackatonproject.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HeistSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private  String levelRequired;

    @Column
    private int membersRequired;

    @ManyToOne
    @JoinColumn(name="heist_id")
    private Heist heist;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;

}
