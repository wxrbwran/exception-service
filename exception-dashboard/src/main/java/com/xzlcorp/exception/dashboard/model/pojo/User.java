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
 * 
 * @TableName t_user
 */
@TableName(value ="s_exception.t_user", autoResultMap = true)
@Data
public class User implements Serializable {
    /**
     * 唯一主键
     */
    @TableId
    private Integer id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 激活状态
     */
    private Boolean activated;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 第三方登录信息
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private Object oauth;

    /**
     * user 所属的 organization (多对多)
     */
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] organizations;

    /**
     * user 所属的 project (多对多)
     */
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] projects;

    /**
     * 用户创建时间
     */
    private Date createdAt;

    /**
     * 用户更新时间
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}