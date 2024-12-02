package com.softdevsix.application.services.report;

import com.softdevsix.domain.entities.report.Report;

public interface IReportService {
    void processAndSaveReport(String idProject, String coverageJson);
    void saveReportToDatabase(String idProject, Report report);

}
