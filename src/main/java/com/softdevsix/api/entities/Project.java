package com.softdevsix.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    /**
     * The unique identifier for the project.
     */
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
    private LocalDateTime createdAt;

    /**
     * The timestamp when the project was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * The list of pull requests associated with the project.
     * This establishes a one-to-many relationship between Project and PullRequest entities.
     */
    private List<PullRequest> pullRequests;
}
