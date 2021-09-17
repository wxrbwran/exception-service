package com.xzlcorp.exception.manager.model.query;

import com.xzlcorp.exception.manager.enums.IssueTrendPeriodEnum;
import lombok.Data;

@Data
public class IssuesTrendQuery {

  private String[] ids;

  private String period;
}
