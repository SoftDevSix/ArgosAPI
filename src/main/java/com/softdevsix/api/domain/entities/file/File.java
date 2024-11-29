package com.softdevsix.api.domain.entities.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class File {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileId;
    @Column(name = "file_name")
    private String fileName;
    private String path;
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MethodCoverageResult> methodCoverageResults;
}
