package org.folio.rest.workflow.model.components;

public interface Conditional {

  public String getExpression();

  public void setExpression(String expression);

  public String getAnswer();

  public void setAnswer(String answer);

}
