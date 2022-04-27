package lambda;

import java.util.function.*;
// 2
class Dog {

  private String name = "哮天犬";
  private int food = 100;

  public Dog() {}

  public Dog(String name) {
    this.name = name;
  }

  public static void bark(Dog dog) {
    System.out.println(dog + "叫了");
  }

  public int eat(int num) {
    System.out.println("吃了" + num + "斤狗粮");
    this.food -= num;
    return this.food;
  }

  @Override
  public String toString() {
    return this.name;
  }
}

public class MethodReferenceDemo {
  public static void main(String[] args) {
     // 方法引用
     Consumer<String> consumer = System.out::println;
     consumer.accept("hello world");
     // 静态方法引用
    Consumer<Dog> dogConsumer = Dog::bark;
    Dog dog = new Dog();
    dogConsumer.accept(dog);
    // 非静态方法引用，使用对象实例引用
//    Function<Integer, Integer> function = dog::eat;
//    System.out.println(function.apply(1));

//    UnaryOperator<Integer> unaryOperator = dog::eat;
//    System.out.println(unaryOperator.apply(1));
    IntUnaryOperator intUnaryOperator = dog::eat;
    System.out.println("还剩狗粮" + intUnaryOperator.applyAsInt(2) + "斤");

    // 使用类名来方法引用 Dog::eat
    BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
    System.out.println(eatFunction.apply(dog, 1));

    // 构造函数的方法引用
    Supplier<Dog> supplier = Dog::new;
    System.out.println("创建了新对象" + supplier.get());
    System.out.println("创建了新对象" + supplier.get().eat(1));

    // 带参数的构造函数的方法引用
    Function<String, Dog> dogFunction = Dog::new;
    System.out.println(dogFunction.apply("旺财"));
  }
}
