package stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo4 {
  public static void main(String[] args) {
//    IntStream.range(1, 100).forEach(StreamDemo4::debug);
    IntStream.range(1, 100).parallel().forEach(StreamDemo4::debug);

  }

  public static void debug(int t) {
    System.out.println("t = " + t);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
