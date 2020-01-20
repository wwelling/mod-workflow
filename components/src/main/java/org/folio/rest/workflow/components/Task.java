package org.folio.rest.workflow.components;

import java.util.Set;

import org.folio.rest.workflow.model.Variable;

public interface Task {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

  public boolean isAsyncAfter();

  public void setAsyncAfter(boolean asyncAfter);

  public Set<Variable> getInputVariables();

  public void setInputVariables(Set<Variable> inputVariables);

  public Variable getOutputVariable();

  public void setOutputVariable(Variable outputVariable);

}
