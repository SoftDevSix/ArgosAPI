package com.softdevsix.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatusTest {

    @Test
    void testEnumExists() {
        Status[] values = Status.values();
        assertNotNull(values, "The Status enum should be defined and not null.");
    }
}