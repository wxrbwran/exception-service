package stream;

import java.util.stream.IntStream;

public class StreamDemo1 {
  public static void main(String[] args) {
    // 外部迭代
    int[] nums  = {1,2,3};
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    System.out.println("sum = " + sum);
    // 内部迭代
    // map: 中间操作，返回Stream
    // sum: 终止操作，返回副作用（结果）
    System.out.println(IntStream.of(nums).map(i -> i * 2).sum());
  }
}
