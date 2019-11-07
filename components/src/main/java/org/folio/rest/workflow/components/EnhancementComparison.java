package org.folio.rest.workflow.components;

import javax.persistence.Embeddable;

@Embeddable
public class EnhancementComparison {
  
  private String sourceProperty;
  
  private String targetProperty;

  public EnhancementComparison() {}
  
  public EnhancementComparison(String sourceProperty, String targetProperty) {
    this.sourceProperty = sourceProperty;
    this.targetProperty = targetProperty;
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