package com.softdevsix.argos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProjectRules {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bestPractices_id")
  private BestPracticesRules bestPractices;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codeComplexity_Id")
  private CodeComplexityRules codeComplexity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codeQuality_id")
  private CodeQualityRules codeQuality;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codeSmells_id")
  private CodeSmellsRules codeSmells;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codingStandards_id")
  private CodingStandardsRules codingStandards;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coverage_id")
  private CoverageRules coverage;
}
