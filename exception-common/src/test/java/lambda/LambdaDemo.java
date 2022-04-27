package lambda;

// 1.
@FunctionalInterface
interface interface1 {
  int doubleNumber(int i);

  default int add(int a, int b) {
    return a + b;
  }
}

public class LambdaDemo {
  public static void main(String[] args) {
    interface1 i1 = (i) -> i * 2;
    System.out.println(i1.add(3, 7));

    System.out.println(i1.doubleNumber(20));
    interface1 i2 = i -> i * 2;
    interface1 i3 = (int i) -> i * 2;
    interface1 i4 = (int i) -> {
      return i * 2;
    };
  }
}
