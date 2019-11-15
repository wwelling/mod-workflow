package org.folio.rest.workflow.components;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnhancementMapping {

  @Column(nullable = false)
  private String toProperty;

  @Column(nullable = false)
  private String fromProperty;

  @Column(nullable = false)
  private boolean multiple;

  public EnhancementMapping() {
    multiple = false;
  }

  public EnhancementMapping(String to, String from) {
    this();
    this.toProperty = to;
    this.fromProperty = from;
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