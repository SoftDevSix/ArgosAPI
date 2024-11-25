package com.softdevsix.api;

import com.softdevsix.domain.files.Coverage;
import com.softdevsix.domain.files.File;
import com.softdevsix.domain.files.FileCoverage;
import com.softdevsix.domain.files.Issue;
import com.softdevsix.domain.files.Project;
import com.softdevsix.domain.pullrequests.PullRequest;
import com.softdevsix.domain.types.*;

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
        Project project = new Project(UUID.randomUUID(), "Test Project", "https://github.com/example/project", createdAt, updatedAt, List.of());

        File file = new File(fileId, path, content, language, createdAt, updatedAt, project);

        assertEquals(fileId, file.getId());
        assertEquals(path, file.getPath());
        assertEquals(content, file.getContent());
        assertEquals(language, file.getLanguage());
        assertEquals(createdAt, file.getCreatedAt());
        assertEquals(updatedAt, file.getUpdatedAt());
        assertEquals(project, file.getProject());
    }

    @Test
    void testProjectEntity() {
        UUID projectId = UUID.randomUUID();
        String name = "Test Project";
        String repositoryUrl = "https://github.com/example/project";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        List<PullRequest> pullRequests = List.of();

        Project project = new Project(projectId, name, repositoryUrl, createdAt, updatedAt, pullRequests);

        assertEquals(projectId, project.getProjectId());
        assertEquals(name, project.getName());
        assertEquals(repositoryUrl, project.getRepositoryUrl());
        assertEquals(createdAt, project.getCreatedAt());
        assertEquals(updatedAt, project.getUpdatedAt());
        assertEquals(pullRequests, project.getPullRequests());
    }

    @Test
    void testPullRequestEntity() {
        UUID pullRequestId = UUID.randomUUID();

        PullRequest pullRequest = new PullRequest(pullRequestId);

        assertEquals(pullRequestId, pullRequest.getId());
    }

    @Test
    void testCoverageEntity() {
        UUID coverageId = UUID.randomUUID();
        int totalFiles = 10;
        float overallCoverage = 85.0f;
        float requiredCoverage = 90.0f;
        String codeRating = "A";
        String requiredRating = "B";
        LocalDateTime analysisDate = LocalDateTime.now();
        CoverageStatus status = CoverageStatus.PASSED;
        PullRequest pullRequest = new PullRequest(UUID.randomUUID());

        Coverage coverage = new Coverage(coverageId, totalFiles, overallCoverage, requiredCoverage, codeRating, requiredRating, status, analysisDate, pullRequest);

        assertEquals(coverageId, coverage.getCoverageId());
        assertEquals(totalFiles, coverage.getTotalAnalyzedFiles());
        assertEquals(overallCoverage, coverage.getOverallCoveragePercentage());
        assertEquals(requiredCoverage, coverage.getRequiredCoveragePercentage());
        assertEquals(codeRating, coverage.getCodeRating());
        assertEquals(requiredRating, coverage.getRequiredRating());
        assertEquals(analysisDate, coverage.getAnalysisDate());
        assertEquals(status, coverage.getOverallStatus());
        assertEquals(pullRequest, coverage.getPullRequest());
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
        File file = new File(UUID.randomUUID(), "src/main/File.java", "public class File {}", "Java", LocalDateTime.now(), LocalDateTime.now(), new Project(UUID.randomUUID(), "Test Project", "https://github.com/example/project", LocalDateTime.now(), LocalDateTime.now(), List.of()));

        FileCoverage fileCoverage = new FileCoverage(fileCoverageId, totalLines, status, totalLines, coveragePercentage, generalCoverage, lineCoverage, methodCoverage, classCoverage, file);

        assertEquals(fileCoverageId, fileCoverage.getId());
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
        FileCoverage fileCoverage = new FileCoverage(UUID.randomUUID(), 200, CoverageStatus.PASSED, 200, 80.0f, 85.0f, 90.0f, 75.0f, 95.0f, new File(UUID.randomUUID(), "src/main/File.java", "public class File {}", "Java", LocalDateTime.now(), LocalDateTime.now(), new Project(UUID.randomUUID(), "Test Project", "https://github.com/example/project", LocalDateTime.now(), LocalDateTime.now(), List.of())));

        Issue issue = new Issue(issueId, lineNumber, index, message, issueType, severityType, fileCoverage, createdAt, updatedAt);

        assertEquals(issueId, issue.getId());
        assertEquals(lineNumber, issue.getLineNumber());
        assertEquals(index, issue.getIndex());
        assertEquals(message, issue.getMessage());
        assertEquals(issueType, issue.getType());
        assertEquals(severityType, issue.getSeverity());
        assertEquals(createdAt, issue.getCreatedAt());
        assertEquals(updatedAt, issue.getUpdatedAt());
        assertEquals(fileCoverage, issue.getFileCoverage());
    }
}
