package com.softdevsix.application.services.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.domain.exceptions.ReportReaderException;
import org.springframework.stereotype.Component;

@Component
public class JsonReportReader implements IReportReader {
    @Override
    public Report read(String coverageReport) {

        try {
            return new ObjectMapper().readValue(coverageReport, Report.class);
        } catch (JsonProcessingException e) {
            throw new ReportReaderException("Could not read coverage file", e);
        }
    }
}
