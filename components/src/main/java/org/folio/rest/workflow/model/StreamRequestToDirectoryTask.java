package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.workflow.components.Task;

@Entity
public class StreamRequestToDirectoryTask extends Node implements Task {

  @Embedded
  private EmbeddedRequest request;

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @NotNull
  @Column(nullable = false)
  private String path;

  @NotNull
  @Column(nullable = false)
  private String workflow;

  @NotNull
  @Column(nullable = false)
  private int batchSize;

  @NotNull
  @Size(min = 4, max = 256)
  @Column(nullable = false)
  private String completeMessage;

  @NotNull
  @Size(min = 4, max = 256)
  @Column(nullable = false)
  private String writeSignalMessage;

  @Column(nullable = false)
  private boolean emitWriteSignal;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public StreamRequestToDirectoryTask() {
    super();
    inputVariables = new HashSet<EmbeddedVariable>();
    emitWriteSignal = false;
    asyncBefore = false;
    asyncAfter = false;
  }

  public EmbeddedRequest getRequest() {
    return request;
  }

  public void setRequest(EmbeddedRequest request) {
    this.request = request;
  }

  public Set<EmbeddedVariable> getInputVariables() {
    return inputVariables;
  }

  public void setInputVariables(Set<EmbeddedVariable> inputVariables) {
    this.inputVariables = inputVariables;
  }

  public EmbeddedVariable getOutputVariable() {
    return outputVariable;
  }

  public void setOutputVariable(EmbeddedVariable outputVariable) {
    this.outputVariable = outputVariable;
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

  public int getBatchSize() {
    return batchSize;
  }

  public void setBatchSize(int batchSize) {
    this.batchSize = batchSize;
  }

  public String getCompleteMessage() {
    return completeMessage;
  }

  public void setCompleteMessage(String completeMessage) {
    this.completeMessage = completeMessage;
  }

  public String getWriteSignalMessage() {
    return writeSignalMessage;
  }

  public void setWriteSignalMessage(String writeSignalMessage) {
    this.writeSignalMessage = writeSignalMessage;
  }

  public boolean isEmitWriteSignal() {
    return emitWriteSignal;
  }

  public void setEmitWriteSignal(boolean emitWriteSignal) {
    this.emitWriteSignal = emitWriteSignal;
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
