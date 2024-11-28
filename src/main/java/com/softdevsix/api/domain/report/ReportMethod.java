package com.softdevsix.api.domain.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportMethod {
    private String name;
    private String returnType;
    private List<ReportLine> statements;
}
