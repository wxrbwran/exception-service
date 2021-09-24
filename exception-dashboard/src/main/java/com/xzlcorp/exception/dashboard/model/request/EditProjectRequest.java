package com.xzlcorp.exception.dashboard.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wuxiaoran
 */
@Data
@ApiModel(value="Project",description="编辑项目")
public class EditProjectRequest {

    @NotNull(message="项目名称不能为空")
    @Size(min=2, max=20, message="项目名称长度为2-20")
    @ApiModelProperty(value = "name", notes = "项目名称", required = true)
    private String name;

    @NotNull(message="项目类型不能为空")
    @ApiModelProperty(value = "type", notes = "项目类型", required = true)
    private String type;

}