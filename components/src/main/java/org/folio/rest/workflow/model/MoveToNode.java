package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.Branch;
import org.folio.rest.workflow.model.has.common.HasMoveToNodeCommon;

@Entity
public class MoveToNode extends Node implements Branch, HasMoveToNodeCommon {

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String gatewayId;

  @Getter
  @Setter
  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  public MoveToNode() {
    super();

    nodes = new ArrayList<Node>();
  }

}
