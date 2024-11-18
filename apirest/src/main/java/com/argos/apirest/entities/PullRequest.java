package com.argos.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a pull request in the system.
 * A pull request contains metadata such as its title and description,
 * and it is associated with a specific project.
 */
@Setter
@Getter
@Entity
public class PullRequest {

    /**
     * The unique identifier for the pull request.
     */
    @Id
    @GeneratedValue
    private UUID pullRequestId;

    /**
     * The title of the pull request.
     */
    private String title;

    /**
     * The description of the pull request.
     */
    private String description;

    /**
     * The timestamp when the pull request was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the pull request was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The project to which the pull request belongs.
     * This establishes a many-to-one relationship between PullRequest and Project entities.
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /**
     * Constructor to initialize a PullRequest entity with all required properties.
     *
     * @param pullRequestId The unique identifier for the pull request.
     * @param title The title of the pull request.
     * @param description The description of the pull request.
     * @param createdAt The timestamp when the pull request was created.
     * @param updatedAt The timestamp when the pull request was last updated.
     * @param project The project to which the pull request belongs.
     */
    public PullRequest(UUID pullRequestId, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Project project) {
        this.pullRequestId = pullRequestId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.project = project;
    }

    /**
     * Default constructor for the PullRequest entity.
     */
    public PullRequest() {}
}
