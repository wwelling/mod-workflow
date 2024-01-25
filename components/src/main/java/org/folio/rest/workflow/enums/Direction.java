package org.folio.rest.workflow.enums;

public enum Direction {
  UNSPECIFIED("Unspecified"),
  CONVERGING("Converging"),
  DIVERGING("Diverging"),
  MIXED("Mixed");

  private final String value;

  Direction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
