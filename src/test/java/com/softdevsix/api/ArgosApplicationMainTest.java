package com.softdevsix.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;


class ArgosApplicationMainTest {

    @Test
    void testMain() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            ArgosApplication.main(new String[]{});
            mockedSpringApplication.verify(() -> SpringApplication.run(ArgosApplication.class, new String[]{}));
        }
    }
}
