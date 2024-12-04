package com.softdevsix.application.mappers.responses;

import java.util.UUID;

public record ProjectResponse(
    UUID projectId,
    String name,
    String description
) {
}
