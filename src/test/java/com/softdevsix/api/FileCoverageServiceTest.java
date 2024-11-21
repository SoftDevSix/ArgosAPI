package com.softdevsix.api;

import com.softdevsix.api.DTO.FileCoverageDTO;
import com.softdevsix.api.Entity.ClassData;
import com.softdevsix.api.Entity.MethodData;
import com.softdevsix.api.Repository.CoverageRepository;
import com.softdevsix.api.Service.FileCoverageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class FileCoverageServiceTest {

    @Mock
    private CoverageRepository coverageRepository;

    @InjectMocks
    private FileCoverageService fileCoverageService;

    @Test
    public void testGetFileCoverage() {
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

    /**
     * Test case when JSON is empty.
     */
    @Test
    public void testEmptyJson() {
        Mockito.when(coverageRepository.getClassData()).thenReturn(List.of());

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            fileCoverageService.getFileCoverage("123");
        });

        assertEquals("Error loading JSON data", exception.getMessage());
    }
}
