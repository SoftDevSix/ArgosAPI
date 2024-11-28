package com.softdevsix.api.domain.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportClass {
    private String name;
    private String sourceFileName;
    private List<ReportMethod> methods;
}
