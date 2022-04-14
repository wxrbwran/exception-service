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
 * 项目表
 * @TableName t_project
 */
@TableName(value ="s_exception.t_project", autoResultMap = true)
@Data
public class Project implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String apiKey;

    /**
     * 项目名	
     */
    private String name;

    /**
     * 项目类型
     */
    private String type;

    /**
     * project 的管理员用户(多对一)
     */
    private Integer admin;

    /**
     * project 所属的 organization (多对一)
     */
    private Integer organization;

    /**
     * project 所拥有的 users (多对多)
     */
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] users;

    /**
     * project 所拥有的 notification rules 
     */
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] notificationRules;

    /**
     * project 所拥有的 notification settings
     */
    private Integer notificationSetting;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}