package com.softdevsix.argos.application.services.report;

import com.softdevsix.argos.domain.entities.report.Report;

public interface IReportReader {
    Report read(String path);
}
