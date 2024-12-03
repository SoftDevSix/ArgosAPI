package com.softdevsix.domain.entities.project;

import com.softdevsix.domain.entities.file.File;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID projectId;
    private String name;
    private String description;
    private ProjectParams projectParams;
    private ProjectResults projectResults;
    @Builder.Default
    private List<File> coveredFiles = new ArrayList<>();
}
