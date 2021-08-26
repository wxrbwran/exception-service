package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Invite {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String uuid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String hash;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String auth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String url;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date expires;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer[] projects;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer organization;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer inviter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUuid() {
        return uuid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getHash() {
        return hash;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAuth() {
        return auth;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUrl() {
        return url;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getExpires() {
        return expires;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExpires(Date expires) {
        this.expires = expires;
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
    public Integer getOrganization() {
        return organization;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getInviter() {
        return inviter;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }
}