package com.softdevsix.api;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgosApplicationTest {

    @Test
    void testMainMethod() {
        ArgosApplication.main(new String[]{});

        assertTrue(true);
    }
}
