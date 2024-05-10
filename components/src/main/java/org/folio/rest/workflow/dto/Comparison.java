package org.folio.rest.workflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comparison {

  @NotNull
  private String sourceProperty;

  @NotNull
  private String targetProperty;

  public Comparison() {
    super();
  }

}
