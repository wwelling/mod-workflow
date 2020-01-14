package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
