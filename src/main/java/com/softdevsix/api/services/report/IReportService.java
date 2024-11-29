package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.report.Report;

public interface IReportService {
    void processAndSaveReport(String coverageJson);
    void saveReportToDatabase(Report report);

}
