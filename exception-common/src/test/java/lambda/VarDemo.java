package lambda;

import java.util.function.Consumer;

// 变量引用
public class VarDemo {
  public static void main(String[] args) {
    // final jdk8 后可省略，但实际上是final的
    final String str = "hello world";
    Consumer<String> consumer = s -> System.out.println(s + str);
    consumer.accept("dd ");
  }
}
