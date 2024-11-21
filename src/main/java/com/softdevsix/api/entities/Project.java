package com.softdevsix.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a project in the system.
 * A project contains metadata such as its name and repository URL,
 * and it can have multiple associated pull requests.
 */
@Getter
@Setter
@Entity
public class Project {

    /**
     * The unique identifier for the project.
     */
    @Id
    @GeneratedValue
    private UUID projectId;

    /**
     * The name of the project.
     */
    private String name;

    /**
     * The URL of the repository associated with the project.
     */
    private String repositoryUrl;

    /**
     * The timestamp when the project was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the project was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The list of pull requests associated with the project.
     * This establishes a one-to-many relationship between Project and PullRequest entities.
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PullRequest> pullRequests;

    /**
     * Constructor to initialize a Project entity with all required properties.
     *
     * @param projectId The unique identifier for the project.
     * @param name The name of the project.
     * @param repositoryUrl The URL of the project's repository.
     * @param createdAt The timestamp when the project was created.
     * @param updatedAt The timestamp when the project was last updated.
     * @param pullRequests The list of pull requests associated with the project.
     */
    public Project(UUID projectId, String name, String repositoryUrl, LocalDateTime createdAt, LocalDateTime updatedAt, List<PullRequest> pullRequests) {
        this.projectId = projectId;
        this.name = name;
        this.repositoryUrl = repositoryUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pullRequests = pullRequests;
    }

    /**
     * Default constructor for the Project entity.
     */
    public Project() {
        // Intentionally left empty for JPA use.
    }
}
