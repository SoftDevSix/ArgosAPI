package com.argos.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@Entity
public class PullRequest {

    @Id
    @GeneratedValue
    private UUID pullRequestId;

    private String title;
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public PullRequest(UUID pullRequestId, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Project project) {
        this.pullRequestId = pullRequestId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.project = project;
    }

    public PullRequest() {}

}

