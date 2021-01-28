package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.model.components.Branch;

@Entity
public class MoveToNode extends Node implements Branch {

  @NotNull
  @Column(nullable = false)
  private String gatewayId;

  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  public MoveToNode() {
    super();
    nodes = new ArrayList<Node>();
  }

  public String getGatewayId() {
    return gatewayId;
  }

  public void setGatewayId(String gatewayId) {
    this.gatewayId = gatewayId;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

}
