package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.workflow.model.components.Conditional;

@Entity
public class Condition extends Node implements Conditional {

  @NotNull
  @Size(min = 4, max = 128)
  @Column(nullable = false)
  private String condition;

  @NotNull
  @Size(min = 2, max = 64)
  @Column(nullable = false)
  private String answer;

  public Condition() {
    super();
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
