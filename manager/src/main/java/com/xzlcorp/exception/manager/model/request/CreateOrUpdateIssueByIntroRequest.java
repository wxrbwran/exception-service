package com.xzlcorp.exception.manager.model.request;

import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class CreateOrUpdateIssueByIntroRequest {

  private Event event;

  private String intro;

  private MetaData metaData;

  private Issue baseIssue;

  private String documentId;

  private String index;

}
