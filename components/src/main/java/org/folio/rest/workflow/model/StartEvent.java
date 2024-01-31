package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.folio.rest.workflow.model.components.Event;

@Entity
public class StartEvent extends Node implements Event {

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StartEventType type;

  @Size(min = 4, max = 256)
  @Column(nullable = true)
  private String expression;

  @Column(nullable = false)
  private boolean interrupting;

  @Column(nullable = false)
  private boolean asyncBefore;

  public StartEvent() {
    super();
    interrupting = false;
    asyncBefore = false;
  }

  public StartEventType getType() {
    return type;
  }

  public void setType(StartEventType type) {
    this.type = type;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public boolean isInterrupting() {
    return interrupting;
  }

  public void setInterrupting(boolean interrupting) {
    this.interrupting = interrupting;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
