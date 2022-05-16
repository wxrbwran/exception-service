package multi_thread;

/*
* 1。卖票过程中出现重票，错票问题 ----》 出现了线程安全问题
* 2。原因：卖票过程中，尚未完成时，其他线程参与进来
* 3。解决：加锁，当一个线程操作共享数据时，其他线程不能参与，直道操作完再开始。即使线程A阻塞，也不能被改变
* 4。在java中，同步同步机制，来解决
*  - 同步代码块
*     synchronized (同步监视器:锁) {
*       需要被同步的代码：操作共享数据的代码
*     }
*     任务类的对象，都可以充当锁: 要求多个线程要共用同一把锁
*
*  - 同步方法
*     如果操作共享数据的代码完整的声明在一个方法，
*5。好处：同步的方式解决了问题
*   缺点：只有一个线程，相当于单线程
* */
class WindowRunnable implements Runnable {
  private int ticket = 100;
//  Object obj = new Object();
  @Override
   public void run() {
    while (true) {
      show();
//      synchronized (this) {
//        if (ticket > 0) {
//          try{
//            Thread.sleep(100);
//          } catch (InterruptedException e) {
//            System.out.println("e = " + e);
//          }
//          System.out.println(Thread.currentThread().getName() + " = " + ticket);
//          ticket--;
//        } else {
//          break;
//        }
//      }
    }
  }
  // 默认锁：this
  private synchronized void show() {
    if (ticket > 0) {
      try{
        Thread.sleep(100);
      } catch (InterruptedException e) {
        System.out.println("e = " + e);
      }
      System.out.println(Thread.currentThread().getName() + " = " + ticket);
      ticket--;
    }
//    else {
//      Thread.currentThread().interrupt();
//    }
  }
}

public class WindowRunnableTest {
  public static void main(String[] args) {
    WindowRunnable w1 = new WindowRunnable();

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
