package com.softdevsix.argos.application.services.report;

import com.softdevsix.argos.domain.entities.report.Report;

public interface IReportService {
    void processAndSaveReport(String coverageJson);
    void saveReportToDatabase(Report report);

}
