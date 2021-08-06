package com.xzlcorp.exception.common.model.pojo.event;

import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class Detail {

  private String message;
  private String name;
  private String filename;
  private String lineno;
  private String colno;
  private String stack;

  private String outerHTML;
  private String src;
  private String tagName;
  private String id;
  private String classname;

  private String nodeType;

  private String selector;

  private DetailAjaxReq req;

  private DetailAjaxRes res;

  private String url;
  private String protocol;
  private String extensions;

  private String binaryType;
  private String timeStamp;
  private String readyState;
  private String bufferedAmount;
  private String errorInfo;
  private String component;
  private String file;
  private String props;

  private String level;

  private String path;
  private String query;

  private Boolean isEntryPage;

}
