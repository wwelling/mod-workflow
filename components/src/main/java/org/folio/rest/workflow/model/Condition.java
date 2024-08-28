package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.Conditional;

@Entity
public class Condition extends Node implements Conditional {

  @Getter
  @Setter
  @NotNull
  @Size(min = 2, max = 64)
  @Column(nullable = false)
  private String answer;

  @Getter
  @Setter
  @NotNull
  @Size(min = 4, max = 128)
  @Column(nullable = false)
  private String expression;

  public Condition() {
    super();

    answer = "";
    expression = "";
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (answer == null) {
      answer = "";
    }

    if (expression == null) {
      expression = "";
    }
  }

}
