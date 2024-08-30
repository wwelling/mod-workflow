package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.common.HasEmbeddedLoopReferenceCommon;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
public class EmbeddedLoopReference implements HasEmbeddedLoopReferenceCommon {

  @Getter
  @Setter
  @Column(nullable = true)
  private String cardinalityExpression;

  @Getter
  @Setter
  @Column(nullable = true)
  private String completeConditionExpression;

  @Getter
  @Setter
  @Column(nullable = true)
  private String dataInputRefExpression;

  @Getter
  @Setter
  @Column(nullable = true)
  private String inputDataName;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean parallel;

  public EmbeddedLoopReference() {
    super();

    parallel = false;
  }

  @PrePersist
  public void prePersist() {
    if (parallel == null) {
      parallel = false;
    }
  }

  @Override
  public boolean hasCardinalityExpression() {
    return cardinalityExpression != null;
  }

  @Override
  public boolean hasCompleteConditionExpression() {
    return completeConditionExpression != null;
  }

  @Override
  public boolean hasDataInput() {
    return dataInputRefExpression != null && inputDataName != null;
  }

}
