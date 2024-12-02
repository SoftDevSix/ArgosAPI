package com.softdevsix.argos.domain;

import jakarta.persistence.*;

@Entity
public class BestPracticesRules {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = true)
  private boolean noHardcodedValues;

  public boolean isNoHardcodedValuesEnabled() {
    return noHardcodedValues;
  }

  public void setNoHardcodedValues(boolean noHardcodedValues) {
    this.noHardcodedValues = noHardcodedValues;
  }
}
