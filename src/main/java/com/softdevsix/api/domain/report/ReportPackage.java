package com.softdevsix.api.domain.report;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportPackage {
    private String name;
    @Builder.Default private List<ReportClass> reportClass = new ArrayList<>();
}
