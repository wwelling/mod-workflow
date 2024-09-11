package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
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

    gatewayId = "";
    nodes = new ArrayList<>();
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (gatewayId == null) {
      gatewayId = "";
    }

    if (nodes == null) {
      nodes = new ArrayList<>();
    }
  }

}
