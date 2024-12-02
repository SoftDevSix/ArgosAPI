package com.softdevsix.argos.repository;

import com.softdevsix.argos.domain.Project;
import com.softdevsix.argos.domain.Rules;

import java.util.Optional;

/**
 * RulesRepo
 */
public interface RulesRepo {
  Integer createRule(Rules rules, Project project);
  Optional<Rules> fetchRule(Integer repoId);
}
