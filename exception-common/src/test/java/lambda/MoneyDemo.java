package lambda;

import java.text.DecimalFormat;
import java.util.function.Function;
// 1.
//interface IMoneyFormat{
//  String format(int i);
//}

class MyMoney {
  private final int money;
  public MyMoney(int money) {
    this.money = money;
  }
  
  public void printMoney(Function<Integer, String> moneyFormat) {
    System.out.println("moneyFormat.apply(this.money) = " + moneyFormat.apply(this.money));
  }
}

public class MoneyDemo {
  public static void main(String[] args) {
    MyMoney myMoney = new MyMoney(99999999);
    myMoney.printMoney(i -> new DecimalFormat("#,###").format(i));
  }
}
