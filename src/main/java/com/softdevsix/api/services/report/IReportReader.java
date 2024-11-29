package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.report.Report;

public interface IReportReader {
    Report read(String path);
}
