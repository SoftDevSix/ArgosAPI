package com.softdevsix.domain.entities.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.softdevsix.domain.entities.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_params_id", referencedColumnName = "project_params_id")
    private ProjectParams projectParams;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_results_id", referencedColumnName = "project_results_id")
    private ProjectResults projectResults;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore()
    private List<File> files;
}
