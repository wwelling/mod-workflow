package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasAsync;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
public class Setup implements HasAsync {

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean asyncAfter;

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean asyncBefore;

  public Setup() {
    super();

    asyncAfter = false;
    asyncBefore = false;
  }

  @PrePersist
  public void prePersist() {
    if (asyncAfter == null) {
      asyncAfter = false;
    }

    if (asyncBefore == null) {
      asyncBefore = false;
    }
  }

}
