package com.xzlcorp.exception.common.utils;

import java.util.List;

/**
 * 描述: 削刮PageInfo多出的字段
 *
 * @author wuxiaoran
 */
public class PageInfoReducer {

  public static<T> PageInfoReduce reduce(List<T> list) {
//    PageInfo<T> pageList = new PageInfo<T>(list);
//    PageInfoReduce newList = new PageInfoReduce<T>();
//    newList.setList(list);
//    newList.setTotal(pageList.getTotal());
//    return newList;
    return null;
  }

  public static<T> PageInfoReduce reduce(List<T> list, Long total) {
    PageInfoReduce newList = new PageInfoReduce<T>();
    newList.setList(list);
    newList.setTotal(total);
    return newList;
  }


  public static class PageInfoReduce<T> {

    private Long total;
    private List<T> list;

    public Long getTotal() {
      return total;
    }

    public void setTotal(Long total) {
      this.total = total;
    }

    public List<T> getList() {
      return list;
    }

    public void setList(List<T> list) {
      this.list = list;
    }
  }
}
