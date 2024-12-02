package com.softdevsix.application.services.report;

import com.softdevsix.domain.entities.report.Report;

public interface IReportReader {
    Report read(String path);
}
