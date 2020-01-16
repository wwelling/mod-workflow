package org.folio.rest.workflow.model;

public interface StartEvent extends Event {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

}
