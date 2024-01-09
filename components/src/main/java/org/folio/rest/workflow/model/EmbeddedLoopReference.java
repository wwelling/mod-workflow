package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.common.HasEmbeddedLoopReferenceCommon;

@Embeddable
public class EmbeddedLoopReference implements HasEmbeddedLoopReferenceCommon {

  @Getter
  @Setter
  @Column(nullable = true)
  private String cardinalityExpression;

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
  @Column(nullable = true)
  private String completeConditionExpression;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = true)
  private boolean parallel;

  public EmbeddedLoopReference() {
    super();

    parallel = false;
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
