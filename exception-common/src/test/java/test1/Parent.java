package test1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent {
  public String name;
  Parent(String name){
    this.name = name;
  }
  public void eat() {
    log.info("parent eat");
  }
}
