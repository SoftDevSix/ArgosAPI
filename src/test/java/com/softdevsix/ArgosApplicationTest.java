package com.softdevsix;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ArgosApplicationTest {

    @Autowired
    private ApplicationContext context;

//    @Test
//    void contextLoads() {
//        assertNotNull(context, "The application context should have loaded.");
//    }
//
//    @Test
//    void testMain() {
//        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
//            ArgosApplication.main(new String[]{});
//            mockedSpringApplication.verify(() -> SpringApplication.run(ArgosApplication.class, new String[]{}));
//        }
//    }
}
