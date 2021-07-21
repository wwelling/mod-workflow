package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.folio.rest.workflow.model.components.Gateway;

@MappedSuperclass
public abstract class AbstractGateway extends Node implements Gateway {

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Direction direction;

  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  public AbstractGateway() {
    super();
    direction = Direction.UNSPECIFIED;
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
