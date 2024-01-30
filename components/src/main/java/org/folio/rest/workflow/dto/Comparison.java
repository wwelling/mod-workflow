package org.folio.rest.workflow.dto;

import jakarta.validation.constraints.NotNull;

public class Comparison {

  @NotNull
  private String sourceProperty;

  @NotNull
  private String targetProperty;

  public Comparison() {
    super();
  }

  public String getSourceProperty() {
    return sourceProperty;
  }

  public String getTargetProperty() {
    return targetProperty;
  }

  public void setSourceProperty(String sourceProperty) {
    this.sourceProperty = sourceProperty;
  }

  public void setTargetProperty(String targetProperty) {
    this.targetProperty = targetProperty;
  }

}