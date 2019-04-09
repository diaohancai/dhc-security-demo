package com.hancai.demo.user.dto;

import java.util.Date;

public class User {

    private String id;
    private String name;
    private String password;
    private String role;
    private Date birthday;

    public User() {

    }

    public User(String id, String name, String password, String role, Date birthday) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
