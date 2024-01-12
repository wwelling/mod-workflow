package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
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
  }

}
