package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EmbeddedLoopReference {

  @Column(nullable = true)
  private String cardinalityExpression;

  @Column(nullable = true)
  private String dataInputRefExpression;

  @Column(nullable = true)
  private String inputDataName;

  @Column(nullable = true)
  private String completeConditionExpression;

  @NotNull
  @Column(nullable = true)
  private boolean parallel;

  public EmbeddedLoopReference() {
    super();
    parallel = false;
  }

  public String getCardinalityExpression() {
    return cardinalityExpression;
  }

  public void setCardinalityExpression(String cardinalityExpression) {
    this.cardinalityExpression = cardinalityExpression;
  }

  public boolean hasCardinalityExpression() {
    return cardinalityExpression != null;
  }

  public String getDataInputRefExpression() {
    return dataInputRefExpression;
  }

  public void setDataInputRefExpression(String dataInputRefExpression) {
    this.dataInputRefExpression = dataInputRefExpression;
  }

  public String getInputDataName() {
    return inputDataName;
  }

  public void setInputDataName(String inputDataName) {
    this.inputDataName = inputDataName;
  }

  public boolean hasDataInput() {
    return dataInputRefExpression != null && inputDataName != null;
  }

  public String getCompleteConditionExpression() {
    return completeConditionExpression;
  }

  public void setCompleteConditionExpression(String completeConditionExpression) {
    this.completeConditionExpression = completeConditionExpression;
  }

  public boolean hasCompleteConditionExpression() {
    return completeConditionExpression != null;
  }

  public boolean isParallel() {
    return parallel;
  }

  public void setParallel(boolean parallel) {
    this.parallel = parallel;
  }

}
