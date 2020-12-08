package com.hackatonproject.api.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainSkill")
    private List<Member> member;

    @OneToMany(mappedBy = "skill")
    private Set<MemberSkill> memberSkills;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

    public Set<MemberSkill> getMemberSkills() {
        return memberSkills;
    }

    public void setMemberSkills(Set<MemberSkill> memberSkills) {
        this.memberSkills = memberSkills;
    }
}