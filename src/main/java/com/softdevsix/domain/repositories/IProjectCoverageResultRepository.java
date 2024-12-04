package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.coverage.ProjectCoverageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProjectCoverageResultRepository extends JpaRepository<ProjectCoverageResult, UUID> {
}
