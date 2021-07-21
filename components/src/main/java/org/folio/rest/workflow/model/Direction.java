package org.folio.rest.workflow.model;

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
