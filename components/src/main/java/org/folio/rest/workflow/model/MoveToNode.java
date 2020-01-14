package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class MoveToNode extends Node implements Branch {

  @NotNull
  @Column(nullable = false)
  private String nodeId;

  public MoveToNode() {
    super();
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

}
