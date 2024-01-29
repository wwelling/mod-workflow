package org.folio.rest.workflow.dto;

import jakarta.validation.constraints.NotNull;

public class Mapping {

  @NotNull
  private String toProperty;

  @NotNull
  private String fromProperty;

  @NotNull
  private boolean multiple;

  public Mapping() {
    super();
    multiple = false;
  }

  public String getToProperty() {
    return toProperty;
  }

  public void setToProperty(String toProperty) {
    this.toProperty = toProperty;
  }

  public String getFromProperty() {
    return fromProperty;
  }

  public void setFromProperty(String fromProperty) {
    this.fromProperty = fromProperty;
  }

  public boolean isMultiple() {
    return multiple;
  }

  public void setMultiple(boolean multiple) {
    this.multiple = multiple;
  }

}