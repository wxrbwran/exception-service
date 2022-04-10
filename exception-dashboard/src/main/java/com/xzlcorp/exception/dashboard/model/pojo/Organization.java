package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Organization {

    private Integer id;

    private String name;

    private String introduction;

    private Integer admin;

    private Integer[] projects;

    private Integer count;

    private Date createdAt;

    private Date updatedAt;


    private Integer[] users;


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
        this.name = name == null ? null : name.trim();
    }


    public String getIntroduction() {
        return introduction;
    }


    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }


    public Integer getAdmin() {
        return admin;
    }


    public void setAdmin(Integer admin) {
        this.admin = admin;
    }


    public Integer[] getProjects() {
        return projects;
    }


    public void setProjects(Integer[] projects) {
        this.projects = projects;
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Integer[] getUsers() {
        return users;
    }


    public void setUsers(Integer[] users) {
        this.users = users;
    }
}