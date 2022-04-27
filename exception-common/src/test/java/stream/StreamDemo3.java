package stream;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo3 {
  public static void main(String[] args) {
    String str = "my name is 007";
//    Stream.of(str.split(" "))
//        .map(String::length)
//        .forEach(System.out::println);
//
//    Stream.of(str.split(" "))
//        .filter(s -> s.length() > 2)
//        .map(String::length)
//        .forEach(System.out::println);

    // flatMap A.B 属性(是个集合)，最终得到所有的A元素里面的B属性集合
    Stream.of(str.split(" ")).flatMap(s -> {
      Stream<Integer> boxed = s.chars().boxed();
      return boxed;
    }).forEach(c -> {
      System.out.println((char) c.intValue());
    });
    int[][] intArr = {{1,2}, {3,4}};
    Arrays.stream(intArr).flatMapToInt(Arrays::stream).forEach(System.out::println);
    // peek 用于debug，是个中间操作

    //forEachOrdered //保证顺序
    Arrays.stream(intArr).parallel().flatMapToInt(Arrays::stream).forEachOrdered(System.out::println);
    // reduce
    int asInt = Arrays.stream(intArr).flatMapToInt(Arrays::stream).reduce(Integer::sum).getAsInt();
    int reduce = Arrays.stream(intArr).flatMapToInt(Arrays::stream).reduce(0, Integer::sum);

    System.out.println("asInt = " + asInt);
    System.out.println("reduce = " + reduce);

    Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
    System.out.println("max = " + max.orElse(""));


  }
}
