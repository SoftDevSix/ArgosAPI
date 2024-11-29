package com.softdevsix.api.domain.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportPackage {
    private String name;
    private List<ReportClass> reportClass;
}
