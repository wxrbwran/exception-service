package multi_thread;

class WindowRunnable implements Runnable {
  private int ticket = 100;
  @Override
  public void run() {
    while (true) {
      if (ticket > 0) {
        System.out.println(Thread.currentThread().getName() + " = " + ticket);
        ticket--;
      } else {
        break;
      }

    }
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
