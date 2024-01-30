package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import org.folio.rest.workflow.model.components.Navigation;

@Entity
public class ConnectTo extends Node implements Navigation {

  @NotNull
  @Column(nullable = false)
  private String nodeId;

  public ConnectTo() {
    super();
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

}
