package multi_thread;

/*
*
* 解决线程安全问题的方式3：Lock锁 ----jdk5.0 新增
*
* synchronized 与 Lock 的异同
* 都解决线程安全问题
* 不同：synchronized 自动释放同步监视器
*       Lock需要手动启动同步，手动结束同步
* 优先使用顺序：Lock 同步代码块 同步方法
* */

import java.util.concurrent.locks.ReentrantLock;

class WindowLock implements Runnable {

  private int ticket = 100;
  // 实例化lock
  private ReentrantLock lock = new ReentrantLock(true);
  @Override
  public void run() {
    while (true) {
      try {
        lock.lock();
        if (ticket > 0) {
          try{
            Thread.sleep(100);
          } catch (InterruptedException e) {
            System.out.println("e = " + e);
          }
          System.out.println(Thread.currentThread().getName() + " 卖票 " + ticket);
          ticket--;
        } else {
          break;
        }
      } finally {
        lock.unlock();
      }
    }
  }
}


public class LockTest {
  public static void main(String[] args) {
    WindowLock w1 = new WindowLock();

    Thread thread1 = new Thread(w1);
    thread1.setName("t1");
    Thread thread2 = new Thread(w1);
    thread2.setName("t2");
    Thread thread3 = new Thread(w1);
    thread3.setName("t3");

    thread1.start();
    thread2.start();
    thread3.start();

  }
}
