package com.softdevsix.api;

import com.softdevsix.api.dtos.FileCoverageDTO;
import com.softdevsix.api.entities.ClassData;
import com.softdevsix.api.entities.MethodData;
import com.softdevsix.api.exception.FileCoverageException;
import com.softdevsix.api.exception.JsonDataLoadException;
import com.softdevsix.api.repositories.CoverageRepository;
import com.softdevsix.api.services.FileCoverageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class FileCoverageServiceTest {

    @Mock
    private CoverageRepository coverageRepository;

    @InjectMocks
    private FileCoverageService fileCoverageService;

    @Test
    void testGetFileCoverage() {
        List<MethodData> methods = new ArrayList<>();

        MethodData method1 = new MethodData();
        method1.setName("<init>()V");
        method1.setDesc("Constructor");
        method1.setAllStatements(List.of(1, 2, 3, 4, 5));
        method1.setCoveragedStatements(List.of(4, 5));
        methods.add(method1);

        MethodData method2 = new MethodData();
        method2.setName("main");
        method2.setDesc("Main method");
        method2.setAllStatements(List.of(6, 7));
        method2.setCoveragedStatements(List.of(6));
        methods.add(method2);

        ClassData classData = new ClassData();
        classData.setClassName("App.java");
        classData.setPathClass("src/org/example/App");
        classData.setMethods(methods);

        Mockito.when(coverageRepository.getClassData()).thenReturn(List.of(classData));

        FileCoverageDTO result = fileCoverageService.getFileCoverage("123");

        assertEquals("App.java", result.getFileName());
        assertEquals("src/org/example/App", result.getPathFile());
        assertEquals("42.86%", result.getCoveragePercentage());
        assertEquals(7, result.getLinesCode());
    }

    @Test
    void testEmptyJson() {
        Mockito.when(coverageRepository.getClassData()).thenReturn(List.of());

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            fileCoverageService.getFileCoverage("123");
        });

        assertEquals("Error loading JSON data", exception.getMessage());
    }

    @Test
    void testInvalidJsonThrowsCustomException() {
        Mockito.doThrow(new JsonDataLoadException("Error loading JSON data from file", new IOException("Invalid JSON")))
                .when(coverageRepository).getClassData();

        JsonDataLoadException exception = Assertions.assertThrows(
                JsonDataLoadException.class,
                () -> coverageRepository.getClassData()
        );

        assertEquals("Error loading JSON data from file", exception.getMessage());

        Throwable cause = exception.getCause();
        assertTrue(cause instanceof IOException);
        assertEquals("Invalid JSON", cause.getMessage());
    }

    @Test
    void testEmptyClassDataThrowsException() {
        Mockito.when(coverageRepository.getClassData()).thenReturn(List.of());

        FileCoverageException exception = Assertions.assertThrows(
                FileCoverageException.class,
                () -> fileCoverageService.getFileCoverage("123")
        );

        assertEquals("Error loading JSON data", exception.getMessage());
    }

}
