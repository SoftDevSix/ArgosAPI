package com.softdevsix.api.services.report;

import com.softdevsix.api.Utils;
import com.softdevsix.api.domain.report.Report;
import com.softdevsix.api.repositories.FileMemoryRepository;
import com.softdevsix.api.repositories.IFileRepository;
import com.softdevsix.api.repositories.IProjectRepository;
import com.softdevsix.api.repositories.ProjectRepository;
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
        assertEquals(2+3, fileRepository.getAll().size());
    }
}