package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.report.Report;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final JsonReportReader reportReader;

    public ReportService(JsonReportReader reportReader) {
        this.reportReader = reportReader;
    }

    public void processAndSaveReport(String coverageJson) {
        Report report = reportReader.read(coverageJson);
        saveReportToDatabase(report);
    }

    public void saveReportToDatabase(Report report) {
        // TODO
        // Logic for saving the report to the database
        // A database repository will be used here (e.g. JPA or MongoDB)
    }
}

