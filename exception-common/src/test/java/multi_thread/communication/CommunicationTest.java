package multi_thread.communication;

/**
 * 只能出现在同步代码块或者同步方法中
 * wait()：当前线程进入阻塞状态，并释放同步监视器
 * notifyAll() 唤醒所有被wait的线程
 * notify() 唤醒被wait的一个线程，多个则唤醒优先级高的
 */


class Number implements Runnable{
  private int num = 1;

  @Override
  public void run() {
    while (true) {
      synchronized (this) {
        notifyAll();
//        notify();
        if (num <= 100) {
          System.out.println(Thread.currentThread().getName() + " num = " + num);
          num++;
          try {
            // wait会释放锁
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          break;
        }
      }
    }
  }
}

public class CommunicationTest {
  public static void main(String[] args) {
    Number number = new Number();
    Thread t1 = new Thread(number);
    Thread t2 = new Thread(number);

    t1.start();
    t2.start();
  }


}
