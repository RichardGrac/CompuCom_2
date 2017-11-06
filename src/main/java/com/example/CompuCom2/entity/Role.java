package com.example.CompuCom2.entity;

import com.example.CompuCom2.model.UserRoleType;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role{

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="type")
    private String type = UserRoleType.USER.getUserProfileType();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

