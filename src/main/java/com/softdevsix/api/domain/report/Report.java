package com.softdevsix.api.domain.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Report {
    private String name;
    private List<ReportPackage> reportPackages;
}
