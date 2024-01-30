package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasAsync;
import org.folio.rest.workflow.model.has.HasNodes;


@Entity
public class EventSubprocess extends Node implements HasAsync, HasNodes {

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncBefore;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncAfter;

  @Getter
  @Setter
  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  public EventSubprocess() {
    super();

    nodes = new ArrayList<Node>();
  }

}
