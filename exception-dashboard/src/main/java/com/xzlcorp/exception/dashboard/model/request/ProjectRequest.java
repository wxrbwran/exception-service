package com.xzlcorp.exception.dashboard.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
@ApiModel(value="Project",description="创建项目")
public class ProjectRequest {

    @NotNull(message="项目名称不能为空")
    @Size(min=2, max=20, message="项目名称长度为2-20")
    @ApiModelProperty(value="name",name="名称", required = true)
    private String name;

    @NotNull(message="项目类型不能为空")
    @ApiModelProperty(value="type",name="项目类型", required = true)
    private String type;

    @NotNull(message="项目管理员不能为空")
    @ApiModelProperty(value="admin",name="项目管理员", required = true)
    private Integer admin;

    @NotNull(message="项目所在组织机构不能为空")
    @ApiModelProperty(value="organization",name="项目所在组织机构", required = true)
    private Integer organization;

}