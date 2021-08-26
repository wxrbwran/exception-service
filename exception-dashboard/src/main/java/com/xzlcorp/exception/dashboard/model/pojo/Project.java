package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Project {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer organization;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] users;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] notificationRules;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer notificationSetting;

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
    public String getApiKey() {
        return apiKey;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
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
    public String getType() {
        return type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
    public Integer getOrganization() {
        return organization;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getUsers() {
        return users;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsers(Integer[] users) {
        this.users = users;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getNotificationRules() {
        return notificationRules;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNotificationRules(Integer[] notificationRules) {
        this.notificationRules = notificationRules;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getNotificationSetting() {
        return notificationSetting;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNotificationSetting(Integer notificationSetting) {
        this.notificationSetting = notificationSetting;
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