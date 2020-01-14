package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SetupTask extends Node implements Task {

  @Column(nullable = false)
  private Boolean loadInitialContext;

  public SetupTask() {
    super();
  }

  public Boolean getLoadInitialContext() {
    return loadInitialContext;
  }

  public void setLoadInitialContext(Boolean loadInitialContext) {
    this.loadInitialContext = loadInitialContext;
  }

}
