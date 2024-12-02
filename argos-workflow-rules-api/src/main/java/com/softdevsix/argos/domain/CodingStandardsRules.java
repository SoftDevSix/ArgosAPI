package com.softdevsix.argos.domain;

import jakarta.persistence.*;

@Entity
public class CodingStandardsRules {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = true)
  private boolean camelCaseNaming;

  @Column(nullable = true)
  private boolean pascalCaseForClasses;

  @Column(nullable = true)
  private boolean bracesOnSameLine;

  public boolean isCamelCaseNamingEnabled() {
    return camelCaseNaming;
  }

  public void setCamelCaseNaming(boolean camelCaseNaming) {
    this.camelCaseNaming = camelCaseNaming;
  }

  public boolean isPascalCaseForClassesEnabled() {
    return pascalCaseForClasses;
  }

  public void setPascalCaseForClasses(boolean pascalCaseForClasses) {
    this.pascalCaseForClasses = pascalCaseForClasses;
  }

  public boolean isBracesOnSameLineEnabled() {
    return bracesOnSameLine;
  }

  public void setBracesOnSameLine(boolean bracesOnSameLine) {
    this.bracesOnSameLine = bracesOnSameLine;
  }
}
