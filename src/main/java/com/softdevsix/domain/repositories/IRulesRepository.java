package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.project.ProjectParams;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRulesRepository{
    ProjectParams createRule(ProjectParams params);
    ProjectParams updateRule(ProjectParams params);
    ProjectParams findById(UUID paramsId);
    List<ProjectParams> getAll();
    boolean deleteRule(UUID paramsId);

}
