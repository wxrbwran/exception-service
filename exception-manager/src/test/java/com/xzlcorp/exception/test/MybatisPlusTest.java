package com.xzlcorp.exception.test;

import com.xzlcorp.exception.manager.ExceptionManagerApplication;
import com.xzlcorp.exception.manager.model.dao.DocumentMapper;
import com.xzlcorp.exception.manager.model.dao.IssueMapper;
import com.xzlcorp.exception.manager.model.pojo.Document;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ExceptionManagerApplication.class)
public class MybatisPlusTest {

  @Autowired
  private IssueMapper issueMapper;

  @Test
  public void testSelectList() {
    List<Issue> issueList = issueMapper.selectList(null);
    for (Issue issue : issueList) {
      System.out.println("issue = " + issue);
    }
  }

}
