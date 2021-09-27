package com.xzlcorp.exception.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayToList<T> {
  public static <T> List<T> toList(T[] arrays) {
    List<T> list = new ArrayList<>();
    list.addAll(Arrays.asList(arrays));
    return list;
  }
}
