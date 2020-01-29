package org.folio.rest.workflow.model.components;

import java.util.Set;

import org.folio.rest.workflow.model.EmbeddedVariable;

public interface DelegateTask extends Task {

  public Set<EmbeddedVariable> getInputVariables();

  public void setInputVariables(Set<EmbeddedVariable> inputVariables);

  public EmbeddedVariable getOutputVariable();

  public void setOutputVariable(EmbeddedVariable outputVariable);

}
