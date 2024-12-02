package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.report.Report;

public interface IReportService {
    void processAndSaveReport(String idProject, String coverageJson);
    void saveReportToDatabase(String idProject, Report report);

}
