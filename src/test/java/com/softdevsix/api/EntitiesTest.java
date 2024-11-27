package com.softdevsix.api;
import com.softdevsix.api.entities.*;
import com.softdevsix.api.types.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {

    @Test
    void testFileEntity() {
        UUID fileId = UUID.randomUUID();
        String path = "src/main/File.java";
        String content = "public class File {}";
        String language = "Java";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Project project = new Project();

        File file = new File(fileId, path, content, language, createdAt, updatedAt, project);

        assertEquals(fileId, file.getFileId());
        assertEquals(path, file.getPath());
        assertEquals(content, file.getContent());
        assertEquals(language, file.getLanguage());
        assertEquals(createdAt, file.getCreatedAt());
        assertEquals(updatedAt, file.getUpdatedAt());
        assertEquals(project, file.getProject());

        File fileDefault = new File();
        fileDefault.setFileId(fileId);
        fileDefault.setPath(path);
        fileDefault.setContent(content);
        fileDefault.setLanguage(language);
        fileDefault.setCreatedAt(createdAt);
        fileDefault.setUpdatedAt(updatedAt);
        fileDefault.setProject(project);

        assertEquals(fileId, fileDefault.getFileId());
        assertEquals(path, fileDefault.getPath());
        assertEquals(content, fileDefault.getContent());
        assertEquals(language, fileDefault.getLanguage());
        assertEquals(createdAt, fileDefault.getCreatedAt());
        assertEquals(updatedAt, fileDefault.getUpdatedAt());
        assertEquals(project, fileDefault.getProject());
    }

    @Test
    void testProjectEntity() {
        UUID projectId = UUID.randomUUID();
        String name = "Test Project";
        int requiredCoveragePercentage = 85;
        String requiredRating = "B";

        Project project = new Project(projectId, name, requiredCoveragePercentage, requiredRating);

        assertEquals(projectId, project.getProjectId());
        assertEquals(name, project.getName());
        assertEquals(requiredCoveragePercentage, project.getRequiredCoveragePercentage());
        assertEquals(requiredRating, project.getRequiredRating());

        Project defaultProject = new Project();
        defaultProject.setProjectId(projectId);
        defaultProject.setName(name);
        defaultProject.setRequiredCoveragePercentage(requiredCoveragePercentage);
        defaultProject.setRequiredRating(requiredRating);

        assertEquals(projectId, defaultProject.getProjectId());
        assertEquals(name, defaultProject.getName());
        assertEquals(requiredCoveragePercentage, defaultProject.getRequiredCoveragePercentage());
        assertEquals(requiredRating, defaultProject.getRequiredRating());
    }

    @Test
    void testPullRequestEntity() {
        UUID pullRequestId = UUID.randomUUID();
        String title = "PR Title";
        String description = "PR Description";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Project project = new Project();

        PullRequest pullRequest = new PullRequest(pullRequestId, title, description, createdAt, updatedAt, project);

        assertEquals(pullRequestId, pullRequest.getPullRequestId());
        assertEquals(title, pullRequest.getTitle());
        assertEquals(description, pullRequest.getDescription());
        assertEquals(createdAt, pullRequest.getCreatedAt());
        assertEquals(updatedAt, pullRequest.getUpdatedAt());
        assertEquals(project, pullRequest.getProject());
    }

    @Test
    void testCoverageEntity() {
        UUID coverageId = UUID.randomUUID();
        int totalAnalyzedFiles = 10;
        float overallCoveragePercentage = 85.0f;
        String codeRating = "A";
        CoverageStatus overallStatus = CoverageStatus.PASSED;
        LocalDateTime analysisDate = LocalDateTime.now();

        Coverage coverage = new Coverage(coverageId, totalAnalyzedFiles, overallCoveragePercentage, codeRating, overallStatus, analysisDate);

        assertEquals(coverageId, coverage.getCoverageId());
        assertEquals(totalAnalyzedFiles, coverage.getTotalAnalyzedFiles());
        assertEquals(overallCoveragePercentage, coverage.getOverallCoveragePercentage(), 0.01);
        assertEquals(codeRating, coverage.getCodeRating());
        assertEquals(overallStatus, coverage.getOverallStatus());
        assertEquals(analysisDate, coverage.getAnalysisDate());

        // Test default constructor and setters
        Coverage defaultCoverage = new Coverage();
        defaultCoverage.setCoverageId(coverageId);
        defaultCoverage.setTotalAnalyzedFiles(totalAnalyzedFiles);
        defaultCoverage.setOverallCoveragePercentage(overallCoveragePercentage);
        defaultCoverage.setCodeRating(codeRating);
        defaultCoverage.setOverallStatus(overallStatus);
        defaultCoverage.setAnalysisDate(analysisDate);

        assertEquals(coverageId, defaultCoverage.getCoverageId());
        assertEquals(totalAnalyzedFiles, defaultCoverage.getTotalAnalyzedFiles());
        assertEquals(overallCoveragePercentage, defaultCoverage.getOverallCoveragePercentage(), 0.01);
        assertEquals(codeRating, defaultCoverage.getCodeRating());
        assertEquals(overallStatus, defaultCoverage.getOverallStatus());
        assertEquals(analysisDate, defaultCoverage.getAnalysisDate());
    }

    @Test
    void testFileCoverageEntity() {
        UUID fileCoverageId = UUID.randomUUID();
        CoverageStatus status = CoverageStatus.PASSED;
        int totalLines = 200;
        float coveragePercentage = 80.0f;
        float generalCoverage = 85.0f;
        float lineCoverage = 90.0f;
        float methodCoverage = 75.0f;
        float classCoverage = 95.0f;
        File file = new File();

        FileCoverage fileCoverage = new FileCoverage();
        fileCoverage.setFileCoverageId(fileCoverageId);
        fileCoverage.setStatus(status);
        fileCoverage.setTotalLines(totalLines);
        fileCoverage.setCoveragePercentage(coveragePercentage);
        fileCoverage.setCoverageGeneral(generalCoverage);
        fileCoverage.setLineCoverage(lineCoverage);
        fileCoverage.setMethodCoverage(methodCoverage);
        fileCoverage.setClassCoverage(classCoverage);
        fileCoverage.setFileId(file);

        assertEquals(fileCoverageId, fileCoverage.getFileCoverageId());
        assertEquals(status, fileCoverage.getStatus());
        assertEquals(totalLines, fileCoverage.getTotalLines());
        assertEquals(coveragePercentage, fileCoverage.getCoveragePercentage());
        assertEquals(generalCoverage, fileCoverage.getCoverageGeneral());
        assertEquals(lineCoverage, fileCoverage.getLineCoverage());
        assertEquals(methodCoverage, fileCoverage.getMethodCoverage());
        assertEquals(classCoverage, fileCoverage.getClassCoverage());
        assertEquals(file, fileCoverage.getFileId());
    }

    @Test
    void testIssueEntity() {
        UUID issueId = UUID.randomUUID();
        int lineNumber = 42;
        int index = 5;
        String message = "NullPointerException possible";
        IssueType issueType = IssueType.DUPLICATE_CODE;
        SeverityType severityType = SeverityType.HIGH;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        FileCoverage fileCoverage = new FileCoverage();

        Issue issue = new Issue();
        issue.setIssueId(issueId);
        issue.setLineNumber(lineNumber);
        issue.setIndex(index);
        issue.setMessage(message);
        issue.setIssueType(issueType);
        issue.setSeverityType(severityType);
        issue.setCreatedAt(createdAt);
        issue.setUpdatedAt(updatedAt);
        issue.setFileCoverage(fileCoverage);

        assertEquals(issueId, issue.getIssueId());
        assertEquals(lineNumber, issue.getLineNumber());
        assertEquals(index, issue.getIndex());
        assertEquals(message, issue.getMessage());
        assertEquals(issueType, issue.getIssueType());
        assertEquals(severityType, issue.getSeverityType());
        assertEquals(createdAt, issue.getCreatedAt());
        assertEquals(updatedAt, issue.getUpdatedAt());
        assertEquals(fileCoverage, issue.getFileCoverage());
    }
}
