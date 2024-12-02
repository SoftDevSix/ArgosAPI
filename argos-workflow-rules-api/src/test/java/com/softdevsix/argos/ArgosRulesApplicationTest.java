package com.softdevsix.argos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ArgosRulesApplicationTest {

  @Autowired
  private ApplicationContext context;

  @Test
  void contextLoads() {
    assertNotNull(context, "The application context should have loaded.");
  }

  @Test
  void testMainMethod() {
    try {
      ArgosRulesApplication.main(new String[] {});
    } catch (Exception e) {
      fail("Main method threw an exception: " + e.getMessage());
    }
  }

}
