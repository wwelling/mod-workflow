package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ScheduleTrigger extends Trigger {

  @NotNull
  @Column(nullable = false)
  private String chronExpression;

  public ScheduleTrigger() {
    super();
  }

  public String getChronExpression() {
    return chronExpression;
  }

  public void setChronExpression(String chronExpression) {
    this.chronExpression = chronExpression;
  }
}
