package com.softdevsix.api.domain.entities.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "fileId")
    private File file;
    @OneToMany(mappedBy = "fileCoverageResult", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MethodCoverageResult> allStatements;
}
