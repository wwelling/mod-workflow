package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.VariableType;
import org.folio.rest.workflow.model.has.common.HasEmbeddedVariableCommon;

@Embeddable
public class EmbeddedVariable implements HasEmbeddedVariableCommon {

  @Getter
  @Setter
  @NotNull
  @Size(min = 4, max = 64)
  @Column(nullable = true)
  private String key;

  @Getter
  @Setter
  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  private VariableType type;

  @Getter
  @Setter
  @Column(nullable = true)
  private boolean spin;

  @Getter
  @Setter
  @Column(nullable = true)
  private Boolean asJson;

  @Getter
  @Setter
  @Column(nullable = true)
  private Boolean asTransient;

  public EmbeddedVariable() {
    type = VariableType.PROCESS;
    spin = false;
    asJson = false;
    asTransient = false;
  }

}
