package org.folio.rest.workflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mapping {

  @NotNull
  private String toProperty;

  @NotNull
  private String fromProperty;

  @NotNull
  private boolean multiple;

  public Mapping() {
    super();

    multiple = false;
  }

}