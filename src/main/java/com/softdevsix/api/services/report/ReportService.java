package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.report.Report;
import com.softdevsix.api.mappers.json.ProjectMapper;
import com.softdevsix.api.repositories.IFileRepository;
import com.softdevsix.api.repositories.IProjectRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Value
public class ReportService implements IReportService {

    JsonReportReader reportReader;
    IProjectRepository projectRepository;
    IFileRepository fileRepository;

    public ReportService(JsonReportReader reportReader, @Qualifier("projectRepository") IProjectRepository projectRepository, @Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
        this.reportReader = reportReader;
        this.projectRepository = projectRepository;
        this.fileRepository = fileRepository;
    }

    public void processAndSaveReport(String idProject, String coverageJson) {
        Report report = reportReader.read(coverageJson);
        saveReportToDatabase(idProject, report);
    }

    public void saveReportToDatabase(String idProject, Report report) {
        ProjectMapper mapper = new ProjectMapper();
        Project project = mapper.handleReport(report);
        project.setProjectId(UUID.fromString(idProject));
        projectRepository.save(project);
        for (File coveredFile : project.getCoveredFiles()) {
            fileRepository.createFile(coveredFile);
        }
    }
}

