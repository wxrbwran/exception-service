package test1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Child extends Parent{
  public Integer age;
  Child(String name, Integer age) {
    super(name);
    this.age = age;
  }

  @Override
  public void eat () {
    log.info("child eat, age is {}", this.age);
  }
}
