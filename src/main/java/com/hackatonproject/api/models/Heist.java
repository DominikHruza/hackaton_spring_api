package com.hackatonproject.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Heist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column
    private String location;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @OneToMany(mappedBy = "heist")
    private List<HeistSkill> heistSkills;

    @OneToMany(mappedBy = "skill")
    private List<MemberSkill> memberSkills;


    public Heist(String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
