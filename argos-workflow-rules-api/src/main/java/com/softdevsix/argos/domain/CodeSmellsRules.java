package com.softdevsix.argos.domain;

import jakarta.persistence.*;

@Entity
public class CodeSmellsRules {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = true)
  private boolean excessiveParameters;

  @Column(nullable = true)
  private boolean magicNumbers;

  @Column(nullable = true)
  private boolean methodTooLong;

  @Column(nullable = true)
  private boolean noDuplicatedCode;

  @Column(nullable = true)
  private int maxMethodLength;

  @Column(nullable = true)
  private int maxParameters;

  public boolean isNoDuplicatedCodeEnabled() {
    return noDuplicatedCode;
  }

  public void setNoDuplicatedCode(boolean noDuplicatedCode) {
    this.noDuplicatedCode = noDuplicatedCode;
  }

  public boolean isMethodTooLongEnabled() {
    return methodTooLong;
  }

  public void setMethodTooLong(boolean methodTooLong) {
    this.methodTooLong = methodTooLong;
  }

  public int getMaxMethodLength() {
    return maxMethodLength;
  }

  public void setMaxMethodLength(int maxMethodLength) {
    this.maxMethodLength = maxMethodLength;
  }

  public boolean isExcessiveParametersEnabled() {
    return excessiveParameters;
  }

  public void setExcessiveParameters(boolean excessiveParameters) {
    this.excessiveParameters = excessiveParameters;
  }

  public int getMaxParameters() {
    return maxParameters;
  }

  public void setMaxParameters(int maxParameters) {
    this.maxParameters = maxParameters;
  }

  public boolean isMagicNumbersEnabled() {
    return magicNumbers;
  }

  public void setMagicNumbers(boolean magicNumbers) {
    this.magicNumbers = magicNumbers;
  }
}
