package com.softdevsix.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class File {

    /**
     * The unique identifier for the file.
     */
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
    private LocalDateTime createdAt;

    /**
     * The timestamp indicating when the file was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * The project to which the file belongs.
     */
    private Project project;
}
