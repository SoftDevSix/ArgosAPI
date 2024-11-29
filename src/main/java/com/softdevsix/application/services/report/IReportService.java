package com.softdevsix.application.services.report;

import com.softdevsix.domain.entities.report.Report;

public interface IReportService {
    void processAndSaveReport(String coverageJson);
    void saveReportToDatabase(Report report);

}
