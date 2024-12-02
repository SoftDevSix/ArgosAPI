package com.softdevsix.argos.application.mappers.json;

import com.softdevsix.argos.domain.entities.file.File;
import com.softdevsix.argos.domain.entities.project.Project;
import com.softdevsix.argos.domain.entities.report.Report;
import com.softdevsix.argos.domain.entities.report.ReportPackage;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.softdevsix.argos.infrastructure.utils.Utils.makeReport;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectMapperTest {

    @Test
    void checkReportPackageCollectionHappyPath() {
        List<ReportPackage> reportPackages = makeReport().getReportPackages();
        ProjectMapper projectMapper = new ProjectMapper();
        List<File> projectCoverageFileList = projectMapper.handleReportPackageList(reportPackages);
        assertEquals(2, projectCoverageFileList.size());
        assertEquals("Car.java", projectCoverageFileList.get(0).getFileName());
        assertEquals("App.java", projectCoverageFileList.get(1).getFileName());
    }

    @Test
    void checkReportMapHappyPath(){
        Report report = makeReport();
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = projectMapper.handleReport(report);
        assertEquals("org/example", project.getName());
        assertEquals(2, project.getCoveredFiles().size());
    }
}