package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.NotNull;

import org.folio.rest.workflow.model.components.Branch;
import org.folio.rest.workflow.model.components.MultiInstance;

@Entity
public class Subprocess extends Node implements Branch, MultiInstance {

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private SubprocessType type;

  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  @Embedded
  private EmbeddedLoopReference loopRef;

  public Subprocess() {
    super();
    nodes = new ArrayList<Node>();
  }

  public SubprocessType getType() {
    return type;
  }

  public void setType(SubprocessType type) {
    this.type = type;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

  public boolean isAsyncAfter() {
    return asyncAfter;
  }

  public void setAsyncAfter(boolean asyncAfter) {
    this.asyncAfter = asyncAfter;
  }

  public EmbeddedLoopReference getLoopRef() {
    return loopRef;
  }

  public void setLoopRef(EmbeddedLoopReference loopRef) {
    this.loopRef = loopRef;
  }

}
