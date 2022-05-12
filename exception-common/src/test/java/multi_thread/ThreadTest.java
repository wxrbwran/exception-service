package multi_thread;

/*
* 多线程的创建 一
* 1. 创建一个Thread的子类
* 2. 重写Thread类的run方法
* 3. 创建子类对象，调用start方法
 */

/*
* 常用方法
* start()
* run()
* currentThread() 静态方法
* getName()
* setName() 构造器也可以命名
* yield() 释放当前cpu的执行权
* join()  在线程a中调用线程b的join方法，此时线程a就进入阻塞状态，直到线程b完全执行后，线程a才结束阻塞状态
* stop() 强制结束线程，已废弃
* sleep(ms) 线程休眠
* isAlive()
* */

/*
* 线程的优先级
* MAX_PRIORITY 10
* NORM_PRIORITY 5 默认
* MIN_PRIORITY 1
* getPriority()
* setPriority()
* 高优先级线程抢占低优先级线程cpu的执行权，但是只是从概率上讲，高优先级线程执行几率高
* */
class MyThread extends Thread { // 1
  @Override //2
  public void run() {
    for(int i = 0; i < 100; i = i + 2) {

      System.out.println(Thread.currentThread().getName() + " i = " + i);
      if (i % 20 == 0) {
        yield();
      }
    }
  }
}

public class ThreadTest {
  public static void main(String[] args) {
    MyThread myThread = new MyThread();// 3
    // start: 1. 启动当前线程
    // 2. jvm调用run方法
    myThread.start();
    myThread.setName("myThread");
    System.out.println(myThread.getName() + " ********************");
    for(int i = 1; i < 100; i = i + 2) {
      System.out.println(Thread.currentThread().getName() + " i = " + i);
      if (i == 21) {
        try {
          myThread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
