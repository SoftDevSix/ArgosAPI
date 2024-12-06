package com.softdevsix.application.services.report;

import com.softdevsix.infrastructure.utils.Utils;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.domain.repositories.IFileRepository;
import com.softdevsix.domain.repositories.IProjectRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ReportServiceTest {

    @Test
    void populateRepositoriesHappyPath() {
        Report report = Utils.makeReport();

        IProjectRepository projectRepository = mock(IProjectRepository.class);
        IFileRepository fileRepository = mock(IFileRepository.class);

        ReportService reportService = new ReportService(null, projectRepository, fileRepository);
        reportService.saveReportToDatabase("6570409c-44d0-4ca5-b271-fe433a0a290a", report);
        assertEquals(0, projectRepository.findAll().size());
    }
}
