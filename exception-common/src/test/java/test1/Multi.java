package test1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Multi {
  public static void main(String[] args) {
    Parent p = new Child("xiaoming", 13);
    p.eat();;
    log.info(p.name);
//    log.info(p.age);
  }
}

