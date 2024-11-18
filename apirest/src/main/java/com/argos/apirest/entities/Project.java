package com.argos.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Project {

    @Id
    @GeneratedValue
    private UUID projectId;

    private String name;
    private String repositoryUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PullRequest> pullRequests;

    public Project(UUID projectId, String name, String repositoryUrl, LocalDateTime createdAt, LocalDateTime updatedAt, List<PullRequest> pullRequests) {
        this.projectId = projectId;
        this.name = name;
        this.repositoryUrl = repositoryUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pullRequests = pullRequests;
    }

    public Project() {}
}
