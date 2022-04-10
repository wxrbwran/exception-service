package com.xzlcorp.exception.manager.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import java.util.Date;
import javax.annotation.Generated;

//@TableName("s_exception.t_issue")
public class Issue {

    private Integer id;


    private String intro;


    private String apiKey;


    private String type;


    private MetaData metadata;


    private String[] events;


    private Integer eventsCount;


    private Integer[] users;


    private Integer usersCount;


    private Date createdAt;


    private Date updatedAt;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getIntro() {
        return intro;
    }


    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }


    public String getApiKey() {
        return apiKey;
    }


    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public MetaData getMetadata() {
        return metadata;
    }


    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }


    public String[] getEvents() {
        return events;
    }


    public void setEvents(String[] events) {
        this.events = events;
    }


    public Integer getEventsCount() {
        return eventsCount;
    }


    public void setEventsCount(Integer eventsCount) {
        this.eventsCount = eventsCount;
    }


    public Integer[] getUsers() {
        return users;
    }


    public void setUsers(Integer[] users) {
        this.users = users;
    }


    public Integer getUsersCount() {
        return usersCount;
    }


    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
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