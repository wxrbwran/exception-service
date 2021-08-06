package com.xzlcorp.exception.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuxiaoran
 */
public class ArrayListUtils {

  public static<T> List<T> toList(T[] lists) {
    List<T> newList = new ArrayList<>();
    if (lists == null) {
      return newList;
    }
    newList.addAll(Arrays.asList(lists));
    return newList;
  }

//  public static<T> T[] toArray(List<T> lists) {
//    if (lists == null) {
//      return null;
//    }
//    return lists.toArray(new T[lists.size()]);
//  }
}
