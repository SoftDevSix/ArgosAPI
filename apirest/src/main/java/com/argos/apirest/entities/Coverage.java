package com.argos.apirest.entities;

import com.argos.apirest.types.CoverageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Coverage {

    @Id
    @GeneratedValue
    private UUID coverageId;

    private int totalAnalyzedFiles;
    private float coveragePercentage;
    private float requiredCoveragePercentage;
    private String codeRating;
    private String requiredCodeRating;

    @Enumerated(EnumType.STRING)
    private CoverageStatus coverageStatus;

    @Column(name = "analysis_date", nullable = false)
    private LocalDateTime analysisDate;

    @ManyToOne
    @JoinColumn(name = "pull_request_id", nullable = false)
    private PullRequest pullRequest;

    public Coverage(UUID coverageId, int totalAnalyzedFiles, float coveragePercentage, float requiredCoveragePercentage, String codeRating, String requiredCodeRating, CoverageStatus coverageStatus, LocalDateTime analysisDate, PullRequest pullRequest) {
        this.coverageId = coverageId;
        this.totalAnalyzedFiles = totalAnalyzedFiles;
        this.coveragePercentage = coveragePercentage;
        this.requiredCoveragePercentage = requiredCoveragePercentage;
        this.codeRating = codeRating;
        this.requiredCodeRating = requiredCodeRating;
        this.coverageStatus = coverageStatus;
        this.analysisDate = analysisDate;
        this.pullRequest = pullRequest;
    }

    public Coverage() {}
}
