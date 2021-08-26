package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Issue {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String intro;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private MetaData metadata;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] events;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer eventsCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] users;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer usersCount;

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
    public String getIntro() {
        return intro;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
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
    public String getType() {
        return type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public MetaData getMetadata() {
        return metadata;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer[] getEvents() {
        return events;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEvents(Integer[] events) {
        this.events = events;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getEventsCount() {
        return eventsCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEventsCount(Integer eventsCount) {
        this.eventsCount = eventsCount;
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
    public Integer getUsersCount() {
        return usersCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
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