package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class MessageCorrelationStartEvent extends Node implements StartEvent {

  @NotNull
  @Size(min = 4, max = 256)
  @Column(nullable = false)
  private String message;

  public MessageCorrelationStartEvent() {
    super();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
