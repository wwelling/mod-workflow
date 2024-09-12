package org.folio.rest.workflow.model.has.common;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.EmbeddedLoopReference EmbeddedLoopReference}.
 */
public interface HasEmbeddedLoopReferenceCommon {

  public String getCardinalityExpression();
  public String getCompleteConditionExpression();
  public String getDataInputRefExpression();
  public String getInputDataName();
  public Boolean getParallel();
  public boolean hasCardinalityExpression();
  public boolean hasCompleteConditionExpression();
  public boolean hasDataInput();

  public void setCardinalityExpression(String cardinalityExpression);
  public void setCompleteConditionExpression(String completeConditionExpression);
  public void setDataInputRefExpression(String dataInputRefExpression);
  public void setInputDataName(String inputDataName);
  public void setParallel(Boolean parallel);

}
