package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.Wait;
import org.folio.rest.workflow.model.has.HasAsync;

@Entity
public class ReceiveTask extends Node implements HasAsync, Wait {

  @Getter
  @Setter
  @NotNull
  @Size(min = 4, max = 256)
  @Column(nullable = false)
  private String message;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncBefore;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncAfter;

  public ReceiveTask() {
    super();

    asyncBefore = false;
    asyncAfter = false;
  }

}
