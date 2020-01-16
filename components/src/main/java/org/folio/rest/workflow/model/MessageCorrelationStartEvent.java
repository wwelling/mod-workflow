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

  @Column(nullable = false)
  private boolean asyncBefore;

  public MessageCorrelationStartEvent() {
    super();
    asyncBefore = true;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
