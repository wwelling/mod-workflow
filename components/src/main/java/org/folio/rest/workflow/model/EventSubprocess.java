package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
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
