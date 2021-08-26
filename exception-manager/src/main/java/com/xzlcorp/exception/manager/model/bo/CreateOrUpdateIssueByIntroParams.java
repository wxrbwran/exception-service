package com.xzlcorp.exception.manager.model.bo;

import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class CreateOrUpdateIssueByIntroParams {

  private Event event;

  private Issue baseIssue;

  private String intro;

  private MetaData metaData;

  private String documentId;

  private String index;

}
