package com.xzlcorp.exception.common.utils;


import com.xzlcorp.exception.common.enums.EventIndicesEnum;
import com.xzlcorp.exception.common.model.es.Indices;
import com.xzlcorp.exception.common.model.pojo.event.Event;

public class ESutils {

  public static EventIndicesEnum getIndexOrKeyByEvent(Event event) {
    String category = event.getCategory();
    switch (category) {
      case "message":
        return EventIndicesEnum.MESSAGE;
      case "feedback":
        return EventIndicesEnum.FEEDBACK;
      case "view":
        return EventIndicesEnum.VIEW;
      case "performance":
        return EventIndicesEnum.PERFORMANCE;
      case "error":
      default:
        return EventIndicesEnum.ERROR;
    }
  }
}
