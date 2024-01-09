package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
