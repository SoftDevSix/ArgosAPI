package com.softdevsix.api.domain.entities.file;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private List<MethodCoverageResult> allStatements;
}
