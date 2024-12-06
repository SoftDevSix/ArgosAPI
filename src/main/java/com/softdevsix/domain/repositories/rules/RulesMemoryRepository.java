package com.softdevsix.domain.repositories.rules;

import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.entities.staticanalysis.Rating;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RulesMemoryRepository implements IRulesRepository {
    private final Map<UUID, ProjectParams> projectRules;

    public RulesMemoryRepository() {
        this.projectRules = new HashMap<>();
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