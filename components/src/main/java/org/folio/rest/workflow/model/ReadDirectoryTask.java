package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.components.Task;

@Entity
public class ReadDirectoryTask extends Node implements Task {

  @NotNull
  @Column(nullable = false)
  private String path;

  @NotNull
  @Column(nullable = false)
  private String workflow;

  @NotNull
  @Column(nullable = false)
  private String outputKey;

  @Column(nullable = false)
  private Boolean useCacheOutput;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public ReadDirectoryTask() {
    super();
    useCacheOutput = false;
    asyncBefore = false;
    asyncAfter = false;
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

  public String getOutputKey() {
    return outputKey;
  }

  public void setOutputKey(String outputKey) {
    this.outputKey = outputKey;
  }

  public Boolean getUseCacheOutput() {
    return useCacheOutput;
  }

  public void setUseCacheOutput(Boolean useCacheOutput) {
    this.useCacheOutput = useCacheOutput;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

  public boolean isAsyncAfter() {
    return asyncAfter;
  }

  public void setAsyncAfter(boolean asyncAfter) {
    this.asyncAfter = asyncAfter;
  }

}
