package com.softdevsix.domain.entities.staticanalysis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    void testEnumValues() {
        Rating[] expectedValues = {Rating.A, Rating.B, Rating.C, Rating.D};
        Rating[] actualValues = Rating.values();

        assertArrayEquals(expectedValues, actualValues, "The values in the enumeration do not match those expected.");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Rating.A, Rating.valueOf("A"), "Value A was not obtained correctly.");
        assertEquals(Rating.B, Rating.valueOf("B"), "Value B was not obtained correctly.");
        assertEquals(Rating.C, Rating.valueOf("C"), "Value C was not obtained correctly.");
        assertEquals(Rating.D, Rating.valueOf("D"), "Value D was not obtained correctly.");
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Rating.valueOf("E"), "An exception was expected when searching for a non-existent value.");
    }
}
