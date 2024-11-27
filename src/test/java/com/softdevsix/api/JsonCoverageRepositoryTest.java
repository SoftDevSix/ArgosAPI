package com.softdevsix.api;

import com.softdevsix.api.entities.ClassData;
import com.softdevsix.api.exception.JsonDataLoadException;
import com.softdevsix.api.repositories.JsonCoverageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonCoverageRepositoryTest {

    @Test
    void testJsonCoverageRepositoryThrowsExceptionWhenFileNotFound() {

        JsonCoverageRepository repository = new JsonCoverageRepository() {
            @Override
            public List<ClassData> getClassData() {
                throw new JsonDataLoadException("File not found");
            }
        };

        JsonDataLoadException exception = Assertions.assertThrows(
                JsonDataLoadException.class,
                repository::getClassData
        );

        assertEquals("File not found", exception.getMessage());
    }

    @Test
    void testJsonCoverageRepositoryHandlesEmptyJsonFile() {
        JsonCoverageRepository repository = new JsonCoverageRepository() {
            @Override
            public List<ClassData> getClassData() {
                return List.of();
            }
        };

        List<ClassData> result = repository.getClassData();

        assertTrue(result.isEmpty());
    }

    @Test
    void testJsonCoverageRepositoryThrowsExceptionForIOException() {

        JsonCoverageRepository repository = mock(JsonCoverageRepository.class);
        when(repository.getClassData()).thenThrow(new JsonDataLoadException("Error loading JSON data", new IOException("I/O Exception")));

        Assertions.assertThrows(JsonDataLoadException.class, () -> repository.getClassData(),
                "Expected JsonDataLoadException to be thrown due to IOException.");
    }

}
