package multi_thread;

class Window extends Thread {
//  private int ticket = 100;
  private static int ticket = 100; // 线程不安全
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
