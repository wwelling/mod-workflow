package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.folio.rest.workflow.components.Subprocess;

@Entity
public class EventSubprocess extends Node implements Subprocess {

  @ManyToMany
  private List<Node> nodes;

  public EventSubprocess() {
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
