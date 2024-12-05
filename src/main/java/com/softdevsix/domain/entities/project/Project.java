package com.softdevsix.domain.entities.project;

import com.softdevsix.domain.entities.file.File;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Project {
    private UUID projectId;
    private String name;
    private String description;
    @Builder.Default
    private ProjectParams projectParams = ProjectParams.builder().build();
    @Builder.Default
    private ProjectResults projectResults = ProjectResults.builder().build();
    @Builder.Default
    private List<File> coveredFiles = new ArrayList<>();
}
