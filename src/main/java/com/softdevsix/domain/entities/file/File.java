package com.softdevsix.domain.entities.file;

import com.softdevsix.domain.entities.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "code_lines")
    private int codeLines;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coverage_result_id", referencedColumnName = "id")
    private FileCoverageResult fileCoverageResult;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;
}
