package org.folio.rest.workflow.model.has;

/**
 * This interface provides the Camunda-specific Asynchronous related methods.
 *
 * This should likely be changed in some way once the Camunda-specific functionality is refactored out.
 */
public interface HasAsync {

  public boolean isAsyncAfter();
  public boolean isAsyncBefore();

  public void setAsyncAfter(boolean asyncAfter);
  public void setAsyncBefore(boolean asyncBefore);

}
