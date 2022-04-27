package FlowDemo;



import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.SubmissionPublisher;

public class FlowDemo2 {
  public static void main(String[] args) throws InterruptedException {

  }

}

class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String> {

  private Subscription subscription;

  @Override
  public void subscribe(Subscriber<? super String> s) {

  }

  @Override
  public void onSubscribe(Subscription s) {
    this.subscription = s;
  }

  @Override
  public void onNext(Integer integer) {

  }

  @Override
  public void onError(Throwable t) {

  }

  @Override
  public void onComplete() {

  }
}