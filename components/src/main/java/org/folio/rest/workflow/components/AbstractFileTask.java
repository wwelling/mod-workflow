package org.folio.rest.workflow.components;

public abstract class AbstractFileTask extends Task {

  String path;

  String workflow;

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
