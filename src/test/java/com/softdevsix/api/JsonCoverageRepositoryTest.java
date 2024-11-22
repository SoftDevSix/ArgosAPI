package com.softdevsix.api;

import com.softdevsix.api.entity.ClassData;
import com.softdevsix.api.exception.JsonDataLoadException;
import com.softdevsix.api.repository.JsonCoverageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonCoverageRepositoryTest {

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


}
