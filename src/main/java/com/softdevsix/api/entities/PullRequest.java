package com.softdevsix.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class PullRequest {

    /**
     * The unique identifier for the pull request.
     */
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
    private LocalDateTime createdAt;

    /**
     * The timestamp when the pull request was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * The project to which the pull request belongs.
     * This establishes a many-to-one relationship between PullRequest and Project entities.
     */
    private Project project;
}
