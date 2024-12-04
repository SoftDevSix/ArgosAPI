package com.softdevsix.application.services;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import com.softdevsix.domain.entities.project.Project;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.project.ProjectResults;
import com.softdevsix.domain.entities.project.Status;
import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import com.softdevsix.domain.repositories.IProjectResultsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectResultsService implements IProjectResultsService {
    private final IProjectResultsRepository iProjectResultsRepository;
    private final ICodeAnalysisResultService iCodeAnalysisResultService;
    private final IProjectCoverageResultService iProjectCoverageResultService;
    private final IProjectParamsService iProjectParamsService;

    public ProjectResultsService(IProjectResultsRepository iProjectResultsRepository,
                                 ICodeAnalysisResultService iCodeAnalysisResultService,
                                 IProjectCoverageResultService iProjectCoverageResultService,
                                 IProjectParamsService iProjectParamsService) {
        this.iProjectResultsRepository = iProjectResultsRepository;
        this.iCodeAnalysisResultService = iCodeAnalysisResultService;
        this.iProjectCoverageResultService = iProjectCoverageResultService;
        this.iProjectParamsService = iProjectParamsService;
    }

    @Override
    public Optional<ProjectResults> getProjectResultsById(UUID projectId) {
        Optional<ProjectResults> projectResultsOptional = iProjectResultsRepository.findById(projectId);

        if (projectResultsOptional.isEmpty()) {
            throw new RuntimeException("Cannot found status for project with id: " + projectId);
        }

        return projectResultsOptional;
    }

    @Override
    public Optional<ProjectResults> calculateProjectResults(UUID projectId) {
        Optional<ProjectParams> projectParams = iProjectParamsService.getProjectParamsByProjectId(projectId);

        Optional<CodeAnalysisResult> codeAnalysisResult = iCodeAnalysisResultService.getProjectRatingById(projectId);
        Optional<ProjectCoverageResult> coverageResult = iProjectCoverageResultService.getProjectCoverageById(projectId);

        Status status = calculateProjectStatus(codeAnalysisResult.get(), coverageResult.get(), projectParams.get());

        ProjectResults projectResults = ProjectResults.builder()
                .status(status)
                .codeAnalysisResult(codeAnalysisResult.get())
                .coverageResult(coverageResult.get())
                .build();

        return Optional.of(iProjectResultsRepository.save(projectResults));
    }

    private Status calculateProjectStatus(CodeAnalysisResult codeAnalysisResult,
                                          ProjectCoverageResult coverageResult,
                                          ProjectParams projectParams) {
        if (codeAnalysisResult.getActualRating() == projectParams.getRequiredCodeRating() &&
                coverageResult.getTotalCoverage() >= projectParams.getRequiredCoveragePercentage()) {
            return Status.PASSED;
        } else {
            return Status.FAILED;
        }
    }

//    @Override
//    public Optional<ProjectResults> calculateProjectResults(UUID projectId) {
//        Optional<Project> project = getProjectById(projectId);
//
//        ProjectResults projectResults = project.getProjectResults();
//
//        ProjectCoverageResult coverageResult = projectResults.getCoverageResult();
//        boolean coveragePassed = coverageResult.getTotalCoverage() >= coverageResult.getRequiredCoverage();
//
//        CodeAnalysisResult analysisResult = projectResults.getCodeAnalysisResult();
//        boolean analysisPassed = analysisResult.getActualRating().compareTo(analysisResult.getExpectedRating()) <= 0;
//
//        if (coveragePassed && analysisPassed) {
//            projectResults.setStatus(Status.PASSED);
//        } else {
//            projectResults.setStatus(Status.FAILED);
//        }
//
//        projectRepository.save(project.get());
//    }
}
