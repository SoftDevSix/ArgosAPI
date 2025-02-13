package com.softdevsix.application.services.report;

import com.softdevsix.infrastructure.utils.Utils;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.domain.repositories.file.FileMemoryRepository;
import com.softdevsix.domain.repositories.file.IFileRepository;
import com.softdevsix.domain.repositories.project.IProjectRepository;
import com.softdevsix.domain.repositories.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportServiceTest {

    @Test
    void populateRepositoriesHappyPath() {
        Report report = Utils.makeReport();

        IProjectRepository projectRepository = new ProjectRepository();
        IFileRepository fileRepository = new FileMemoryRepository();

        ReportService reportService = new ReportService(null, projectRepository, fileRepository);
        reportService.saveReportToDatabase("6570409c-44d0-4ca5-b271-fe433a0a290a", report);
        assertEquals(1, projectRepository.getAll().size());
        assertEquals(2, fileRepository.getAll().size());
    }
}