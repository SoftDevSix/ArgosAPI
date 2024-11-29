package com.softdevsix.api.services.report;

import com.softdevsix.api.domain.entities.file.File;
import com.softdevsix.api.domain.entities.project.Project;
import com.softdevsix.api.domain.report.Report;
import com.softdevsix.api.mappers.json.ProjectMapper;
import com.softdevsix.api.repositories.IFileRepository;
import com.softdevsix.api.repositories.IProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final JsonReportReader reportReader;
    private final IProjectRepository projectRepository;
    private final IFileRepository fileRepository;

    public ReportService(JsonReportReader reportReader, IProjectRepository projectRepository, IFileRepository fileRepository) {
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

