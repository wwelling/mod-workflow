package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.StartEventType;
import org.folio.rest.workflow.model.components.Event;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class StartEvent extends Node implements Event {

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean asyncBefore;

  @Getter
  @Setter
  @Size(min = 4, max = 256)
  @Column(nullable = true)
  private String expression;

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean interrupting;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StartEventType type;

  public StartEvent() {
    super();

    asyncBefore = false;
    interrupting = false;
    type = StartEventType.NONE;
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (asyncBefore == null) {
      asyncBefore = false;
    }

    if (interrupting == null) {
      interrupting = false;
    }

    if (type == null) {
      type = StartEventType.NONE;
    }
  }

}
