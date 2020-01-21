package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Mapping {

  @Column(nullable = false)
  private String toProperty;

  @Column(nullable = false)
  private String fromProperty;

  @Column(nullable = false)
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