package com.xzlcorp.exception.manager.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请表
 * @TableName t_invite
 */
@TableName(value ="t_invite")
@Data
public class Invite implements Serializable {
    /**
     * 
     */
    @TableId
    private Object id;

    /**
     * 
     */
    private String uuid;

    /**
     * 
     */
    private String hash;

    /**
     * invite 邀请人的权限
     */
    private String auth;

    /**
     * invite 邀请链接
     */
    private String url;

    /**
     * invite 过期时间
     */
    private Date expires;

    /**
     * invite 链接所对应的项目
     */
    private Object projects;

    /**
     * invite 链接所对应的团队
     */
    private Object organization;

    /**
     * invite 链接所对应的邀请人
     */
    private Object inviter;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}