package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.repositories.rules.IRulesRepository;
import com.softdevsix.domain.repositories.rules.RulesMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RulesMemoryRepositoryTest {
    private IRulesRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RulesMemoryRepository();
    }


    private ProjectParams buildParams() {
        return ProjectParams.builder()
                .id(UUID.randomUUID())
                .projectCoverage(true)
                .build();
    }

    @Test
    void updateProjectRulesTest() {
        ProjectParams params = buildParams();

        repository.createRule(params);

        params.setProjectCoverage(false);

        repository.updateRule(params);

        assertFalse(repository.findById(params.getId()).isProjectCoverage());
    }

    @Test
    void updateProjectWithDifferentIdTest() {
        ProjectParams params = buildParams();

        repository.createRule(params);

        params.setId(UUID.randomUUID());

        assertNull(repository.updateRule(params));
    }

    @Test
    void updateProjectWithNullIdTest() {
        ProjectParams params = buildParams();

        repository.createRule(params);

        params.setId(null);

        assertNull(repository.updateRule(params));
    }
}
