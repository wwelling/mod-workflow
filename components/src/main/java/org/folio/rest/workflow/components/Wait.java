package org.folio.rest.workflow.components;

public interface Wait {

  public boolean isAsyncBefore();

  public void setAsyncBefore(boolean asyncBefore);

  public boolean isAsyncAfter();

  public void setAsyncAfter(boolean asyncAfter);

  public String getMessage();

  public void setMessage(String mesasge);

}
