package com.xzlcorp.exception.manager.model.bo;

import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import java.util.List;
import lombok.Data;

@Data
public class AggregationDataAndMetaData<T> {

  private MetaData metaData;

  private List<T> agg;
}
