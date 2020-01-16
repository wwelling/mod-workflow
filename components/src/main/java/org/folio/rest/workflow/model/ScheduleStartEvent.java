package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ScheduleStartEvent extends Node implements StartEvent {

  @NotNull
  @Column(nullable = false)
  private String chronExpression;

  @Column(nullable = false)
  private boolean asyncBefore;

  public ScheduleStartEvent() {
    super();
    asyncBefore = false;
  }

  public String getChronExpression() {
    return chronExpression;
  }

  public void setChronExpression(String chronExpression) {
    this.chronExpression = chronExpression;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
