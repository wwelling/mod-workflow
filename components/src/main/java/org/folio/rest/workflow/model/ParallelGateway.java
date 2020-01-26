package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.folio.rest.workflow.model.components.Gateway;

@Entity
public class ParallelGateway extends Node implements Gateway {

  @ManyToMany
  private List<Node> nodes;

  public ParallelGateway() {
    super();
    nodes = new ArrayList<Node>();
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

}
