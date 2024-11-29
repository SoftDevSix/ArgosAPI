package com.softdevsix.api.domain.staticanalysis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    void testEnumValues() {
        Rating[] expectedValues = {Rating.A, Rating.B, Rating.C, Rating.D};
        Rating[] actualValues = Rating.values();

        assertArrayEquals(expectedValues, actualValues, "Los valores de la enumeración no coinciden con los esperados.");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Rating.A, Rating.valueOf("A"), "El valor A no se obtuvo correctamente.");
        assertEquals(Rating.B, Rating.valueOf("B"), "El valor B no se obtuvo correctamente.");
        assertEquals(Rating.C, Rating.valueOf("C"), "El valor C no se obtuvo correctamente.");
        assertEquals(Rating.D, Rating.valueOf("D"), "El valor D no se obtuvo correctamente.");
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Rating.valueOf("E"), "Se esperaba una excepción al buscar un valor inexistente.");
    }
}
