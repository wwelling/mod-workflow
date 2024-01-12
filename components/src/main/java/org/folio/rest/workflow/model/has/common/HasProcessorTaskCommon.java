package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.model.EmbeddedProcessor;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.ProcessorTask ProcessorTask}.
 */
public interface HasProcessorTaskCommon {

  public EmbeddedProcessor getProcessor();

  public void setProcessor(EmbeddedProcessor processor);
}
