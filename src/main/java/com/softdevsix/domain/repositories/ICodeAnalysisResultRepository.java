package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.staticanalysis.CodeAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICodeAnalysisResultRepository extends JpaRepository<CodeAnalysisResult, UUID> {
}
