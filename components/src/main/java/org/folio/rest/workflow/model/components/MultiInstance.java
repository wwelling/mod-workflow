package org.folio.rest.workflow.model.components;

import org.folio.rest.workflow.model.EmbeddedLoopReference;

public interface MultiInstance {

  public EmbeddedLoopReference getLoopRef();

  public void setLoopRef(EmbeddedLoopReference loopRef);

  public default boolean isMultiInstance() {
    return getLoopRef() != null && (getLoopRef().hasCardinalityExpression() || getLoopRef().hasDataInput());
  }

}
