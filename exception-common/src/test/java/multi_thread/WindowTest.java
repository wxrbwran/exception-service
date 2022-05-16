package multi_thread;

/*
*
* 使用extends方式解决线程安全时，同步监视器慎用this/同步方法
*
* */

class Window extends Thread {
//  private int ticket = 100;
  private static int ticket = 100; // 线程不安全
  private static Object obj = new Object();
  @Override
  public void run() {
    while (true) {
      show();
//      synchronized (obj) {
//      synchronized(Window.class) {
//        if (ticket > 0) {
//          System.out.println(Thread.currentThread().getName() + " = " + ticket);
//          try{
//            Thread.sleep(100);
//          } catch (InterruptedException e) {
//            System.out.println("e = " + e);
//          }
//          ticket--;
//        } else {
//          break;
//        }
//      }
    }
  }

  // 默认同步监视器/锁：this // 不加static会错误
//  private synchronized void show() {
  // static this -> Window.class
    private static synchronized void show() {
      if (ticket > 0) {
        System.out.println(Thread.currentThread().getName() + " = " + ticket);
        try{
          Thread.sleep(100);
        } catch (InterruptedException e) {
          System.out.println("e = " + e);
        }
        ticket--;
      }
  }
}

public class WindowTest {
  public static void main(String[] args) {
    Window w1 = new Window();
    Window w2 = new Window();
    Window w3 = new Window();
    w1.start();
    w2.start();
    w3.start();
  }
}
