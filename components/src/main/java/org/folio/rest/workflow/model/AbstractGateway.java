package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;
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
