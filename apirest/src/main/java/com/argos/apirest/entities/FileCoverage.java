package com.argos.apirest.entities;

import com.argos.apirest.types.CoverageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Setter
@Getter
@Entity
public class FileCoverage {

    @Id
    @GeneratedValue
    private UUID fileCoverageId;

    @Enumerated(EnumType.STRING)
    private CoverageStatus status;

    private int linesAnalyzed;
    private int totalLines;
    private float coveragePercentage;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File fileId;

    public FileCoverage(UUID fileCoverageId, CoverageStatus status, int linesAnalyzed, int totalLines, float coveragePercentage, File fileId) {
        this.fileCoverageId = fileCoverageId;
        this.status = status;
        this.linesAnalyzed = linesAnalyzed;
        this.totalLines = totalLines;
        this.coveragePercentage = coveragePercentage;
        this.fileId = fileId;
    }

    public FileCoverage() {}

}
