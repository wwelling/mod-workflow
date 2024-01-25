package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasAsync;

@Embeddable
public class Setup implements HasAsync {

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncBefore;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncAfter;

  public Setup() {
    super();

    asyncBefore = false;
    asyncAfter = false;
  }

}
