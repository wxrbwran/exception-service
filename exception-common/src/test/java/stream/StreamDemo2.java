package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo2 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();

    // 从集合创建
    list.stream();
    list.parallelStream();

    // 从数组创建
    IntStream stream = Arrays.stream(new int[]{1, 2, 3});

    // 创建数字流
    IntStream intStream = IntStream.of(1, 2, 3);
    int sum = intStream.peek(System.out::print).sum();
    IntStream intStream1 = IntStream.rangeClosed(1, 10);
    int sum2 = intStream1.peek(System.out::print).sum();

    //使用random创建一个无限流
    DoubleStream doubles = new Random().doubles().limit(25);
    double sum1 = doubles.peek(System.out::println).sum();
    // 自己创建
    Random random = new Random();
    Stream<Integer> limitRandom = Stream.generate(() -> random.nextInt()).limit(25);
  }
}
