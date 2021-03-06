package com.xzlcorp.exception.manager.model.query;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class IssueQuery {
  @NotNull(message="项目Id不能为空")
  private Integer projectId;

  private String apiKey;

  private Integer pageAt = 1;

  private Integer pageSize = 10;

  private Long start;

  private Long end;

}
