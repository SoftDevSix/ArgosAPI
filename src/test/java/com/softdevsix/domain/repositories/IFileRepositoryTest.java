package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.entities.project.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IFileRepositoryTest {
  @Autowired
  private IFileRepository fileRepository;

  @Autowired
  private IProjectRepository projectRepository;

  @Test
  public void testSaveAndRetrieveFile() {
    Project project = Project.builder()
            .name("File Project 2")
            .description("A project for testing files 2")
            .build();
    project = projectRepository.save(project);

    File file = File.builder()
            .fileName("Apple.java")
            .path("/src/main/Apple.java")
            .codeLines(200)
            .fileCoverageResult(FileCoverageResult
                    .builder()
                    .coveragePercentage(35.0f)
                    .methodCoveragePercentage(12.3f)
                    .methodCoverageResults(List.of(MethodCoverageResult
                            .builder()
                            .coveragePercentage(42.0f)
                            .methodStatements(Map.of(1, true))
                            .build()))
                    .build())
            .project(project)
            .build();
    file = fileRepository.save(file);

    Optional<File> retrievedFile = fileRepository.findById(file.getFileId());
    assertTrue(retrievedFile.isPresent());
    assertEquals("Apple.java", retrievedFile.get().getFileName());
    assertEquals("/src/main/Apple.java", retrievedFile.get().getPath());
    assertEquals(200, retrievedFile.get().getCodeLines());
    assertEquals(project.getProjectId(), retrievedFile.get().getProject().getProjectId());
  }
}
