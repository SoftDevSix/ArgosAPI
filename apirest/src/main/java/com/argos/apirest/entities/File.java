package com.argos.apirest.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

}
