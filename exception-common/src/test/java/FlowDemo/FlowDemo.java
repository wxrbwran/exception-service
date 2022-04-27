package FlowDemo;


import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {
  public static void main(String[] args) throws InterruptedException {
    // 1. 定义发布者，发布的数据类型是Integer
    // 直接使用jdk自带的SubmissionPublisher,他实现了Publisher接口

    SubmissionPublisher<Integer> integerSubmissionPublisher = new SubmissionPublisher<>();

    // 2.定义订阅者
    Subscriber<Integer> integerSubscriber = new Subscriber<Integer>(){

      private Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        // 保存订阅关系，需要用它来给发布者响应
        this.subscription = subscription;
        // 请求一个数据
        this.subscription.request(1);
      }

      @Override
      public void onNext(Integer item) {
        // 接受一个数据，处理
        System.out.println("item = " + item);
        this.subscription.request(1);
//        this.subscription.cancel();
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
      }

      @Override
      public void onComplete() {
        System.out.println("end");
      }
    };
    // 3. 发布者和订阅者 建立订阅关系

    integerSubmissionPublisher.subscribe(integerSubscriber);

    // 4. 生产数据并发布

    int data = 111;
    integerSubmissionPublisher.submit(data);
    integerSubmissionPublisher.submit(data);
    integerSubmissionPublisher.submit(data);


    //5. 结束后， 关闭发布者
    integerSubmissionPublisher.close();

    Thread.currentThread().join(1000);
  }
}
