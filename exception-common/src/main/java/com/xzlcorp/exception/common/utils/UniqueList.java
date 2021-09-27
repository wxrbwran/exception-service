package com.xzlcorp.exception.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UniqueList<T> {

  public static <T> List<T> toUnique(List<T> firstList, List<T> secondList) {
    firstList.addAll(secondList);
    return new ArrayList<>(new HashSet<>(firstList));
  }
}
