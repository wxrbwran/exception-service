package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Organization {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String introduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] projects;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer count;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] users;

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
    public String getIntroduction() {
        return introduction;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getAdmin() {
        return admin;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAdmin(Integer admin) {
        this.admin = admin;
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
    public Integer getCount() {
        return count;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCount(Integer count) {
        this.count = count;
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

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getUsers() {
        return users;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsers(Integer[] users) {
        this.users = users;
    }
}