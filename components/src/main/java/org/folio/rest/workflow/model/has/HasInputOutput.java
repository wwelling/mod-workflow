package org.folio.rest.workflow.model.has;

import java.util.Set;
import org.folio.rest.workflow.model.EmbeddedVariable;

/**
 * This interface provides methods associated with Nodes.
 */
public interface HasInputOutput {

  public Set<EmbeddedVariable> getInputVariables();
  public EmbeddedVariable getOutputVariable();

  public void setInputVariables(Set<EmbeddedVariable> inputVariables);
  public void setOutputVariable(EmbeddedVariable outputVariable);

}
