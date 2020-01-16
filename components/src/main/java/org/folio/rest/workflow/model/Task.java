package org.folio.rest.workflow.model;

public interface Task {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

}
