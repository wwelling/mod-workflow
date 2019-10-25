package org.folio.rest.workflow.components;

import javax.persistence.Embeddable;

@Embeddable
public class EnhancementMapping {
  
  private final String to;
  private final String from;

  public EnhancementMapping(String to, String from) {
    this.to = to;
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public String getFrom() {
    return from;
  }
}