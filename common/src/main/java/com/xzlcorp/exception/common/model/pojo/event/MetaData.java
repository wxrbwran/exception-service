package com.xzlcorp.exception.common.model.pojo.event;

import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class MetaData {

  private String type;

  private String message;

  private String filename;

  private String stack;

  private String path;

  private String query;

  private String level;

  private String others;

}
