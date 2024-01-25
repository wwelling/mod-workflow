package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.Direction;
import org.folio.rest.workflow.model.components.Gateway;
import org.folio.rest.workflow.model.has.HasNodes;

@MappedSuperclass
public abstract class AbstractGateway extends Node implements Gateway, HasNodes {

  @Getter
  @Setter
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Direction direction;

  @Getter
  @Setter
  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  AbstractGateway() {
    super();

    direction = Direction.UNSPECIFIED;
    nodes = new ArrayList<Node>();
  }

}
