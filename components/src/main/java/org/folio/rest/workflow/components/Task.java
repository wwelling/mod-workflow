package org.folio.rest.workflow.components;

import java.util.Set;

import org.folio.rest.workflow.model.EmbeddedVariable;

public interface Task {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

  public boolean isAsyncAfter();

  public void setAsyncAfter(boolean asyncAfter);

  public Set<EmbeddedVariable> getInputVariables();

  public void setInputVariables(Set<EmbeddedVariable> inputVariables);

  public EmbeddedVariable getOutputVariable();

  public void setOutputVariable(EmbeddedVariable outputVariable);

}
