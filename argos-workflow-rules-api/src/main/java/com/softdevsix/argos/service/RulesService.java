package com.softdevsix.argos.service;

import com.softdevsix.argos.domain.Project;
import com.softdevsix.argos.domain.Rules;
import com.softdevsix.argos.domain.RulesRequestMap;
import com.softdevsix.argos.repository.RulesRepoImpl;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class RulesService {

  private final RulesRepoImpl rulesRepo;
  private final ProjectValidator projectValidator;
  private final RulesValidator rulesValidator;

  public RulesService(
      RulesRepoImpl rulesRepo,
      ProjectValidator projectValidator) {
        this.rulesRepo = rulesRepo;
        this.projectValidator = projectValidator;
        this.rulesValidator = new RulesValidator();
  }

  public Rules handleRules(RulesRequestMap rulesRequestMap, Integer projectId) {
    Rules validatedRules = rulesValidator.validate(rulesRequestMap);
    Project project = projectValidator.validate(projectId);
    return getRules( rulesRepo.createRule(validatedRules, project));
  }

  public Rules getRules(Integer projectId) {
    return rulesRepo
        .fetchRule(projectId)
        .orElseGet(
            () -> {
              throw new InvalidParameterException("Could not find requested project");
            });
  }
}
