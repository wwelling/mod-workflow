package org.folio.rest.workflow.components;

public interface StartEvent extends Event {

  public boolean isInterrupting();

  public void setInterrupting(boolean interrupting);

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

}
