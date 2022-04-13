package com.xzlcorp.exception.dashboard.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 组织表
 * @TableName t_organization
 */
@TableName(value ="s_exception.t_organization")
@Data
public class Organization implements Serializable {
    /**
     * 唯一主键
     */
    @TableId
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 
     */
    private Integer admin;

    /**
     * organization 所拥有的 project (一对多)
     */
    private Integer[] projects;

    /**
     * organization 当前承载的 event 数
     */
    private Integer count;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    /**
     * organization 所拥有的 user (多对多)
     */
    private Integer[] users;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}