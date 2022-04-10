package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class User {

    private Integer id;


    private String name;


    private String email;


    private String password;


    private Boolean activated;


    private String mobile;


    private String avatar;


    private Object oauth;


    private Integer[] organizations;


    private Integer[] projects;


    private Date createdAt;


    private Date updatedAt;


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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public Boolean getActivated() {
        return activated;
    }


    public void setActivated(Boolean activated) {
        this.activated = activated;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }


    public String getAvatar() {
        return avatar;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }


    public Object getOauth() {
        return oauth;
    }


    public void setOauth(Object oauth) {
        this.oauth = oauth;
    }


    public Integer[] getOrganizations() {
        return organizations;
    }


    public void setOrganizations(Integer[] organizations) {
        this.organizations = organizations;
    }


    public Integer[] getProjects() {
        return projects;
    }


    public void setProjects(Integer[] projects) {
        this.projects = projects;
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
}