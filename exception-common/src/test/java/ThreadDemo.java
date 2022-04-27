public class ThreadDemo {
  public static void main(String[] args) {
    new Thread(() -> System.out.println("ok")).start();
  }
}
