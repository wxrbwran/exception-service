package multi_thread;
/*
*
* 1. 死锁：不同的线程分别占用对方所需的同步资源不放弃，
*   都在等待对方释放自己所需要的同步资源，就形成了死锁。
*
* 2. 出现死锁后，程序不会出现异常，也不会出现提示，所有的线程都处于阻塞状态，无法继续
*
* */
public class DeadLock {
  public static void main(String[] args) {
    StringBuffer s1 = new StringBuffer();
    StringBuffer s2 = new StringBuffer();

    new Thread(){
      @Override
      public void run() {
        synchronized(s1) {
          s1.append("a");
          s2.append("1");
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (s2) {
            s1.append("b");
            s2.append("2");
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
          }
        }
      }
    }.start();

    new Thread(() -> {
      synchronized (s2) {
        s1.append("c");
        s2.append("3");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (s1) {
          s1.append("d");
          s2.append("4");
          System.out.println("s1 = " + s1);
          System.out.println("s2 = " + s2);
        }
      }
    }).start();
  }
}
