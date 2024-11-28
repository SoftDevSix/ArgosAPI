package com.softdevsix.api.domain.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportLine {
    private int statementNumber;
    private boolean isCovered;
}
