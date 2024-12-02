package com.softdevsix.argos;

import com.softdevsix.argos.domain.*;
import com.softdevsix.argos.exception.RuleNotFoundException;
import com.softdevsix.argos.repository.ProjectRepository;
import com.softdevsix.argos.repository.RulesRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RulesRepoTest {

  @Autowired RulesRepoImpl rulesRepo;
  @Autowired ProjectRepository projectRepository;

  @Test
  void verifyRepoWorks() {
    BestPracticesRules bestPractices = new BestPracticesRules();
    CodeComplexityRules codeComplexity = new CodeComplexityRules();
    CodeQualityRules codeQuality = new CodeQualityRules();
    CodeSmellsRules codeSmells = new CodeSmellsRules();
    CodingStandardsRules codingStandards = new CodingStandardsRules();
    CoverageRules coverage = new CoverageRules();

    Rules rules = Rules.builder()
        .bestPractices(bestPractices)
        .codeComplexity(codeComplexity)
        .codeQuality(codeQuality)
        .codeSmells(codeSmells)
        .codingStandards(codingStandards)
        .coverage(coverage)
        .build();

    Project project = new Project();
    projectRepository.save(project);

    Integer projectRuleId = rulesRepo.createRule(rules, project);
    Rules fetchedRule = rulesRepo.fetchRule(projectRuleId).orElseThrow(() -> new RuleNotFoundException("Rule not found"));
    assertNotNull(fetchedRule, "The fetched rule should not be null");
  }

  @Test
  void verifyRuleNotFoundExceptionIsThrown() {
    int invalidRuleId = -1;

    assertThrows(
            RuleNotFoundException.class,
            () -> fetchRuleOrThrow(invalidRuleId)
    );
  }
  private void fetchRuleOrThrow(int ruleId) {
    rulesRepo.fetchRule(ruleId).orElseThrow(() -> new RuleNotFoundException("Rule not found"));
  }
}
