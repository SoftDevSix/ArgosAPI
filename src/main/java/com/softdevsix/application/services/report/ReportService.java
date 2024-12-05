package com.softdevsix.application.services.report;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.application.mappers.json.ProjectMapper;
import com.softdevsix.domain.repositories.IFileRepository;
import com.softdevsix.domain.repositories.IProjectRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Value
public class ReportService implements IReportService {

    JsonReportReader reportReader;
    IProjectRepository projectRepository;
    IFileRepository fileRepository;

//    public ReportService(JsonReportReader reportReader, @Qualifier("projectRepository") IProjectRepository projectRepository, @Qualifier("fileMemoryRepository") IFileRepository fileRepository) {
//        this.reportReader = reportReader;
//        this.projectRepository = projectRepository;
//        this.fileRepository = fileRepository;
//    }

    public ReportService(JsonReportReader reportReader, IProjectRepository projectRepository, IFileRepository fileRepository) {
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
        project.getFiles().forEach(file -> file.setProject(project));
        fileRepository.saveAll(project.getFiles());
    }
}

