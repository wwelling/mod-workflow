package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SetupTask extends Node implements Task {

  @Column(nullable = false)
  private Boolean loadInitialContext;

  @Column(nullable = false)
  private boolean asyncBefore;

  public SetupTask() {
    super();
    asyncBefore = true;
  }

  public Boolean getLoadInitialContext() {
    return loadInitialContext;
  }

  public void setLoadInitialContext(Boolean loadInitialContext) {
    this.loadInitialContext = loadInitialContext;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
