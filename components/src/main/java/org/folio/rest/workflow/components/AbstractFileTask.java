package org.folio.rest.workflow.components;

import javax.persistence.Column;

public abstract class AbstractFileTask extends Task {

  @Column(nullable = false)
  private String path;

  @Column(nullable = false)
  private String workflow;

  public AbstractFileTask() {
    super();
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getWorkflow() {
    return workflow;
  }

  public void setWorkflow(String workflow) {
    this.workflow = workflow;
  }

}
