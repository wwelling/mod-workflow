package org.folio.rest.workflow.components;

import javax.persistence.Embeddable;

@Embeddable
public class EnhancementMapping {
  
  private String toProperty;

  private String fromProperty;

  public EnhancementMapping() {}

  public EnhancementMapping(String to, String from) {
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

}