package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Variable {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(nullable = true)
  private String key;

  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  private VariableType type;

  public Variable() {
    type = VariableType.PROCESS;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public VariableType getType() {
    return type;
  }

  public void setType(VariableType type) {
    this.type = type;
  }

}
