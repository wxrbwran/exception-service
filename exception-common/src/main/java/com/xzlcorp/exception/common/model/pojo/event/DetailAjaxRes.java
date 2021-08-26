package com.xzlcorp.exception.common.model.pojo.event;

import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class DetailAjaxRes {
  private String response;

  private Integer status;

  private String statusText;

}
