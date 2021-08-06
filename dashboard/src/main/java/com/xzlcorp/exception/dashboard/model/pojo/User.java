package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class User {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean activated;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String mobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String avatar;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Object oauth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] organizations;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] projects;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEmail() {
        return email;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPassword() {
        return password;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getActivated() {
        return activated;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMobile() {
        return mobile;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAvatar() {
        return avatar;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Object getOauth() {
        return oauth;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOauth(Object oauth) {
        this.oauth = oauth;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getOrganizations() {
        return organizations;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrganizations(Integer[] organizations) {
        this.organizations = organizations;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getProjects() {
        return projects;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setProjects(Integer[] projects) {
        this.projects = projects;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreatedAt() {
        return createdAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}