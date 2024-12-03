package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RulesMemoryRepository implements IRulesRepository {

    private final Map<UUID, ProjectParams> projectRules;

    public RulesMemoryRepository() {
        this.projectRules = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        ProjectParams rule1 = ProjectParams.builder()
                .id(UUID.randomUUID())
                .projectName("Project Alpha")
                .projectDescription("Description for Project Alpha")
                .projectCoverage(true)
                .requiredCoveragePercentage(75.0f)
                .projectCodeRating(true)
                .requiredCodeRating(Rating.B)
                .build();

        ProjectParams rule2 = ProjectParams.builder()
                .id(UUID.randomUUID())
                .projectName("Project Beta")
                .projectDescription("Description for Project Beta")
                .projectCoverage(false)
                .requiredCoveragePercentage(0.0f)
                .projectCodeRating(true)
                .requiredCodeRating(Rating.C)
                .build();

        ProjectParams rule3 = ProjectParams.builder()
                .id(UUID.randomUUID())
                .projectName("Project Gamma")
                .projectDescription("Description for Project Gamma")
                .projectCoverage(true)
                .requiredCoveragePercentage(85.0f)
                .projectCodeRating(true)
                .requiredCodeRating(Rating.A)
                .build();

        createRule(rule1);
        createRule(rule2);
        createRule(rule3);
    }

    @Override
    public ProjectParams updateRule(ProjectParams rule) {
        if (rule == null || rule.getId() == null) {
            return null;
        }

        if (!projectRules.containsKey(rule.getId())) {
            return null;
        }

        projectRules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public ProjectParams createRule(ProjectParams rule) {
        rule.setId(UUID.randomUUID());
        projectRules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public ProjectParams findById(UUID id) {
        return projectRules.get(id);
    }

    @Override
    public List<ProjectParams> getAll() {
        return new ArrayList<>(projectRules.values());
    }

    @Override
    public boolean deleteRule(UUID id) {
        return projectRules.remove(id) != null;
    }
}