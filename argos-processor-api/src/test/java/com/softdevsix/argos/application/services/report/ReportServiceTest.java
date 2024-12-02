package com.softdevsix.argos.application.services.report;

import com.softdevsix.argos.infrastructure.utils.Utils;
import com.softdevsix.argos.domain.entities.report.Report;
import com.softdevsix.argos.domain.repositories.FileMemoryRepository;
import com.softdevsix.argos.domain.repositories.IFileRepository;
import com.softdevsix.argos.domain.repositories.IProjectRepository;
import com.softdevsix.argos.domain.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportServiceTest {

    @Test
    void populateRepositoriesHappyPath() {
        Report report = Utils.makeReport();

        IProjectRepository projectRepository = new ProjectRepository();
        IFileRepository fileRepository = new FileMemoryRepository();

        ReportService reportService = new ReportService(null, projectRepository, fileRepository);
        reportService.saveReportToDatabase(report);
        assertEquals(1, projectRepository.getAll().size());
        assertEquals(2+3, fileRepository.getAll().size());
    }
}