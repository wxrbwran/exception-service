package com.xzlcorp.exception.dashboard.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler;
import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import lombok.Data;

/**
 * 邀请表
 * @TableName t_invite
 */
@TableName(value ="s_exception.t_invite", autoResultMap = true)
@Data
public class Invite implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] projects;

    /**
     * invite 链接所对应的团队
     */
    private Integer organization;

    /**
     * invite 链接所对应的邀请人
     */
    private Integer inviter;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}