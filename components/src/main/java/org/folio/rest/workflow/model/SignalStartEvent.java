package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.workflow.components.StartEvent;

@Entity
public class SignalStartEvent extends Node implements StartEvent {

  @NotNull
  @Size(min = 4, max = 256)
  @Column(nullable = false)
  private String signal;

  @Column(nullable = false)
  private boolean interrupting;

  @Column(nullable = false)
  private boolean asyncBefore;

  public SignalStartEvent() {
    super();
    interrupting = false;
    asyncBefore = true;
  }

  public String getSignal() {
    return signal;
  }

  public void setSignal(String signal) {
    this.signal = signal;
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
