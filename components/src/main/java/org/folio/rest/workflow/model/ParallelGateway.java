package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.folio.rest.workflow.model.components.Gateway;

@Entity
public class ParallelGateway extends Node implements Gateway {

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Direction direction;

  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  public ParallelGateway() {
    super();
    direction = Direction.CONVERGING;
    nodes = new ArrayList<Node>();
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

}
