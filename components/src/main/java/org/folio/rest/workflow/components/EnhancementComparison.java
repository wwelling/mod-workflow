package org.folio.rest.workflow.components;

import javax.persistence.Embeddable;

@Embeddable
public class EnhancementComparison {
  
  private final String sourceProperty;
  
  private final String targetProperty;
  
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

}