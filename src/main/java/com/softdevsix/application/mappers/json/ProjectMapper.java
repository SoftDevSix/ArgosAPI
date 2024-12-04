package com.softdevsix.application.mappers.json;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.domain.entities.report.ReportPackage;

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
                .files(handleReportPackageList(report.getReportPackages()))
                .build();
    }
}
