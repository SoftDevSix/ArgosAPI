package com.softdevsix.application.services.report;

import com.softdevsix.infrastructure.utils.Utils;
import com.softdevsix.domain.entities.report.Report;
import org.junit.jupiter.api.Test;

class ReportServiceTest {

    @Test
    void populateRepositoriesHappyPath() {
        Report report = Utils.makeReport();

//        IProjectRepository projectRepository = new ProjectRepository();
//        IFileRepository fileRepository = new FileMemoryRepository();
//
//        ReportService reportService = new ReportService(null, projectRepository, fileRepository);
//        reportService.saveReportToDatabase(report);
//        assertEquals(1, projectRepository.getAll().size());
//        assertEquals(2+3, fileRepository.getAll().size());
    }
}