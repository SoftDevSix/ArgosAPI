package com.softdevsix.api.services.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevsix.api.domain.report.Report;
import com.softdevsix.api.exceptions.ReportReaderException;

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
