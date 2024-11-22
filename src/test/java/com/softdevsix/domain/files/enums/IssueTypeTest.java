package com.softdevsix.domain.files.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class IssueTypeTest {

    @Test
    void testEnumExists() {
        IssueType[] values = IssueType.values();
        assertNotNull(values, "The IssueType enum should be defined and not null.");
    }
}