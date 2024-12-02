package com.softdevsix.argos.domain.entities.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void testEnumValues() {
        Status[] expectedValues = {Status.PASSED, Status.FAILED};
        Status[] actualValues = Status.values();

        assertArrayEquals(expectedValues, actualValues, "The values in the enumeration do not match those expected.");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Status.PASSED, Status.valueOf("PASSED"), "PASSED value was not obtained correctly.");
        assertEquals(Status.FAILED, Status.valueOf("FAILED"), "FAILED value was not obtained correctly.");
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf("UNKNOWN"), "An exception was expected when searching for a non-existent value.");
    }
}
