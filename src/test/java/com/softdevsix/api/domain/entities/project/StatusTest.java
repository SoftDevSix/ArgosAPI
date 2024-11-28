package com.softdevsix.api.domain.entities.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void testEnumValues() {
        Status[] expectedValues = {Status.PASSED, Status.FAILED};
        Status[] actualValues = Status.values();

        assertArrayEquals(expectedValues, actualValues, "Los valores de la enumeración no coinciden con los esperados.");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Status.PASSED, Status.valueOf("PASSED"), "El valor PASSED no se obtuvo correctamente.");
        assertEquals(Status.FAILED, Status.valueOf("FAILED"), "El valor FAILED no se obtuvo correctamente.");
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf("UNKNOWN"), "Se esperaba una excepción al buscar un valor inexistente.");
    }
}