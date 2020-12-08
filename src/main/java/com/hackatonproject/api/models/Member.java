package com.hackatonproject.api.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private Character sex;


    @ManyToOne (cascade = CascadeType.ALL)
    private Skill mainSkill;


    @OneToMany(mappedBy = "member")
    private List<MemberSkill> memberSkills;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;



    public Member() {
    }

    public Member(String name, String email, Character sex, Skill mainSkill, List<MemberSkill> memberSkills, MemberStatus status) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.mainSkill = mainSkill;
        this.memberSkills = memberSkills;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Skill getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(Skill mainSkill) {
        this.mainSkill = mainSkill;
    }

    public List<MemberSkill> getMemberSkills() {
        return memberSkills;
    }

    public void setMemberSkills(List<MemberSkill> memberSkills) {
        this.memberSkills = memberSkills;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", mainSkill=" + mainSkill +
                ", memberSkills=" + memberSkills +
                ", status=" + status +
                '}';
    }
}

