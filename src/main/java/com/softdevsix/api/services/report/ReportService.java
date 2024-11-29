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
        // Lógica para guardar el reporte en la base de datos
        // Aquí se usará un repositorio de base de datos (por ejemplo, JPA o MongoDB)
    }
}

