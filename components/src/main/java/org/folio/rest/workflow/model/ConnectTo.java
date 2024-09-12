package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.Navigation;

@Entity
public class ConnectTo extends Node implements Navigation {

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String nodeId;

  public ConnectTo() {
    super();

    nodeId = "";
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (nodeId == null) {
      nodeId = "";
    }
  }

}
