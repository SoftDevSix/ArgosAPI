package com.softdevsix.api.mappers.json;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.report.Report;
import com.softdevsix.api.domain.report.ReportPackage;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {
    public List<File> handleReportPackageList(List<ReportPackage> reportPackageList) {
        List<File> fileList = new ArrayList<>(reportPackageList.size());
        FileMapper fileMapper = new FileMapper();
        for (ReportPackage reportPackage : reportPackageList) {
            List<File> packageClassFileList = fileMapper.handleReportPackage(reportPackage);
            fileList.addAll(packageClassFileList);
        }
        return fileList;
    }

    public Project handleReport(Report report) {
        return Project.builder()
                .name(report.getName())
                .coveredFiles(handleReportPackageList(report.getReportPackages()))
                .build();
    }
}
