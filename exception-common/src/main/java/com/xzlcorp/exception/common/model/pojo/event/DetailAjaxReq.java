package com.xzlcorp.exception.common.model.pojo.event;

import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class DetailAjaxReq {
  private String url;
  private String method;
  private Object data;
}
