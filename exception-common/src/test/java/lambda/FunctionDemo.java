package lambda;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
// 1
public class FunctionDemo {
  public static void main(String[] args) {
    // 断言函数接口
//    Predicate<Integer> predicate = i -> i < 0;
    IntPredicate predicate = i -> i > 0;
    System.out.println(predicate.test(-1));

    // 消费者接口
    // 方法引用
    IntConsumer consumer1 = System.out::println;
//    Consumer<String> consumer = System.out::println;
//    consumer.accept("hello world");
    consumer1.accept(9999);
  }
}
