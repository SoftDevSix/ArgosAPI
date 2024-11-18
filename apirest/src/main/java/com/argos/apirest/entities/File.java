package com.argos.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
public class File {

    @Id
    @GeneratedValue
    private UUID fileId;

    private String path;
    private String content;
    private String language;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public File(UUID fileId, String path, String content, String language, LocalDateTime createdAt, LocalDateTime updatedAt, Project project) {
        this.fileId = fileId;
        this.path = path;
        this.content = content;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.project = project;
    }

    public File() {}
}
