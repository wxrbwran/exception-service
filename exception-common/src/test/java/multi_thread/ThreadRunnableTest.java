package multi_thread;

/*
 * 多线程的创建 二 实现Runnable接口
 * 1. 创建一个实现了Runnable接口的子类
 * 2. 实现实现Runnable接口的抽象run方法
 * 3. 创建实现类对象
 * 4. 将此对象传入Thread类的构造其中，创建Threa类的对象，调用start方法
 */

/*
* 两种方式的比较：
* 开发中优先选择实现Runnable接口的方式
* 1. 实现的方式没有类的单继承的局限性
* 2. 实现的方式更适合多个线程有共享数据的情况
*
* 联系：
*
* Thread类实现了Runnable接口
* 都需要重写run方法
* */

class ThreadRunnable implements Runnable{
  @Override
  public void run() {
    System.out.println("run");
  }
}

public class ThreadRunnableTest {
  public static void main(String[] args) {
    new Thread(new ThreadRunnable()).start();
    new Thread(() -> {
      System.out.println("run2");
    }).start();
  }
}


