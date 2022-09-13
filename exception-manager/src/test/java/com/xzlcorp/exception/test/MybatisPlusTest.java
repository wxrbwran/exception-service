package com.xzlcorp.exception.test;

import com.xzlcorp.exception.manager.ExceptionManagerApplication;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.service.IssueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ExceptionManagerApplication.class)
public class MybatisPlusTest {

  @Autowired
  private IssueService issueService;

  @Test
  public void testSelectList() {
    List<Issue> issueList = issueService.list(null);
    for (Issue issue : issueList) {
      System.out.println("issue = " + issue);
    }
  }

}
