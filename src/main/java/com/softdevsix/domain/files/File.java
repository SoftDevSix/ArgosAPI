package com.softdevsix.domain.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class File {
    private final UUID id;
    private final String path;
    private final String content;
    private final String language;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Project project;
}
