package lambda;

// 3.
@FunctionalInterface
interface IMath {
  int add(int x, int y);
}

@FunctionalInterface
interface IMath2 {
  int sum(int x, int y);
}

public class TypeDemo {
  public static void main(String[] args) {
    // 变量类型定义
    IMath lambda = (x, y) -> x + y;
    // 数组里
    IMath[] lambdas = {(x, y) -> x + y};
    // 墙转
    Object lambda2 = (IMath) ((x, y) -> x + y);
    // 通过返回类型
    IMath create = createLambda();

    TypeDemo typeDemo = new TypeDemo();
    // 当有二义性的时候，使用强转对应的接口解决
    typeDemo.test((IMath) (x, y) -> x + y);
  }

  public void test(IMath math) {
    System.out.println(math.add(2, 6));
  }

  public void test(IMath2 math) {
  }

  public static IMath createLambda() {
    return (x, y) -> x + y;
  }
}
