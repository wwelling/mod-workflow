package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ExclusiveGateway extends Node implements Branch {

  @NotNull
  @Size(min = 4, max = 128)
  @Column(nullable = false)
  private String condition;

  public ExclusiveGateway() {
    super();
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

}
