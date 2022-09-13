package lambda;

import java.util.function.Function;

// 级连表达和柯里化
public class CurryDemo {
  public static void main(String[] args) {
   Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;
    Function<Integer, Integer> curryFn = f.apply(2);
    System.out.println(curryFn.apply(3));
    System.out.println(curryFn.apply(30));
  }
}
