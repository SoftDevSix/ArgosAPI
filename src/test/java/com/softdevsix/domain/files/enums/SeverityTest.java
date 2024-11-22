package com.softdevsix.domain.files.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SeverityTest {

    @Test
    void testEnumExists() {
        Severity[] values = Severity.values();
        assertNotNull(values, "The Severity enum should be defined and not null.");
    }
}