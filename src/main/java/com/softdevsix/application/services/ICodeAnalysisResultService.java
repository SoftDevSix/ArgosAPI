package com.softdevsix.application.services;

import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;

import java.util.Optional;
import java.util.UUID;

public interface ICodeAnalysisResultService {
    Optional<CodeAnalysisResult> getProjectRatingById(UUID projectId);
    Optional<CodeAnalysisResult> calculateProjectRating(UUID projectId);
}
