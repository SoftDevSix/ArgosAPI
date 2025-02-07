package com.softdevsix.domain.entities.report;

import com.softdevsix.domain.exceptions.ReportReaderException;
import com.softdevsix.application.services.report.IReportReader;
import com.softdevsix.application.services.report.JsonReportReader;
import org.junit.jupiter.api.Test;

import static com.softdevsix.infrastructure.utils.Utils.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReportTest {
    @Test
    void verifyReportReader() {
        String jsonFile = readFile("coverage-report.json");
        IReportReader reportReader = new JsonReportReader();
        Report report = reportReader.read(jsonFile);
        assertEquals("org/example", report.getName());
    }

    @Test
    void verifyInvalidJSON() {
        IReportReader reportReader = new JsonReportReader();
        try {
            reportReader.read("%n");
            fail();
        } catch (ReportReaderException e) {
            assertEquals("Could not read coverage file", e.getMessage());
        }
    }
}
