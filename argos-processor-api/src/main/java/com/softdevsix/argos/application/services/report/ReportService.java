package com.softdevsix.argos.application.services.report;

import com.softdevsix.argos.domain.entities.file.File;
import com.softdevsix.argos.domain.entities.project.Project;
import com.softdevsix.argos.domain.entities.report.Report;
import com.softdevsix.argos.application.mappers.json.ProjectMapper;
import com.softdevsix.argos.domain.repositories.IFileRepository;
import com.softdevsix.argos.domain.repositories.IProjectRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

@Service
@Value
public class ReportService implements IReportService {

    JsonReportReader reportReader;
    IProjectRepository projectRepository;
    IFileRepository fileRepository;

    public ReportService(JsonReportReader reportReader, IProjectRepository projectRepository, @Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
        this.reportReader = reportReader;
        this.projectRepository = projectRepository;
        this.fileRepository = fileRepository;
    }

    public void processAndSaveReport(String coverageJson) {
        Report report = reportReader.read(coverageJson);
        saveReportToDatabase(report);
    }

    public void saveReportToDatabase(Report report) {
        ProjectMapper mapper = new ProjectMapper();
        Project project = mapper.handleReport(report);
        projectRepository.save(project);
        for (File coveredFile : project.getCoveredFiles()) {
            fileRepository.createFile(coveredFile);
        }
    }
}

