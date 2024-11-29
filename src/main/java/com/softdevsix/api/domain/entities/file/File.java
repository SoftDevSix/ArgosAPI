package com.softdevsix.api.domain.entities.file;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class File {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileId;
    private String fileName;
    private String path;
    private int lineCode;
    @OneToMany
    private List<MethodCoverageResult> methodCoverageResults;

}
