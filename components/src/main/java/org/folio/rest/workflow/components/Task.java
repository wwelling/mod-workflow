package org.folio.rest.workflow.components;

public interface Task {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

  public boolean isAsyncAfter();

  public void setAsyncAfter(boolean asyncAfter);

}
