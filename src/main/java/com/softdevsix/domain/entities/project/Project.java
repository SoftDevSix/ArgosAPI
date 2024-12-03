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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID projectId;
    private String name;
    private String description;
    private ProjectParams projectParams;
    private ProjectResults projectResults;
    @Builder.Default
    private List<File> coveredFiles = new ArrayList<>();
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.append(projectId).append(" ").append(name).append(" ").append(description).append(" ").append(projectParams).append(" ").append(coveredFiles).append(" ").append(projectResults).toString();
    }
}
