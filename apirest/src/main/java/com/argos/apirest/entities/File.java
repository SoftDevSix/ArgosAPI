package com.argos.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a file within a project. This entity stores details
 * about the file such as its path, content, language, and timestamps for
 * creation and last update.
 */
@Getter
@Setter
@Entity
public class File {

    /**
     * The unique identifier for the file.
     */
    @Id
    @GeneratedValue
    private UUID fileId;

    /**
     * The path to the file within the project.
     */
    private String path;

    /**
     * The content of the file (e.g., source code or other content).
     */
    private String content;

    /**
     * The programming language of the file (e.g., Java, Python, etc.).
     */
    private String language;

    /**
     * The timestamp indicating when the file was created.
     * This field is not updatable once the file is created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp indicating when the file was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The project to which the file belongs.
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /**
     * Constructor to initialize a File entity with all required properties.
     *
     * @param fileId The unique identifier for the file.
     * @param path The path to the file within the project.
     * @param content The content of the file.
     * @param language The programming language of the file.
     * @param createdAt The timestamp when the file was created.
     * @param updatedAt The timestamp when the file was last updated.
     * @param project The project to which the file belongs.
     */
    public File(UUID fileId, String path, String content, String language, LocalDateTime createdAt, LocalDateTime updatedAt, Project project) {
        this.fileId = fileId;
        this.path = path;
        this.content = content;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.project = project;
    }

    /**
     * Default constructor for the File entity.
     */
    public File() {}
}
