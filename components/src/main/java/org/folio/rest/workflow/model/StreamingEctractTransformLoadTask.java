package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.folio.rest.workflow.components.Task;

@Entity
public class StreamingEctractTransformLoadTask extends Node implements Task {

  @ManyToMany
  private Set<Stream> streams;

  @ElementCollection
  private Set<Process> processes;

  @ElementCollection
  private Set<Request> requests;

  @ElementCollection
  private Set<Variable> inputVariables;

  @Embedded
  private Variable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public StreamingEctractTransformLoadTask() {
    streams = new HashSet<Stream>();
    processes = new HashSet<Process>();
    requests = new HashSet<Request>();
    inputVariables = new HashSet<Variable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  public Set<Stream> getStreams() {
    return streams;
  }

  public void setStreams(Set<Stream> streams) {
    this.streams = streams;
  }

  public Set<Process> getProcesses() {
    return processes;
  }

  public void setProcesses(Set<Process> processes) {
    this.processes = processes;
  }

  public Set<Request> getRequests() {
    return requests;
  }

  public void setRequests(Set<Request> requests) {
    this.requests = requests;
  }

  public Set<Variable> getInputVariables() {
    return inputVariables;
  }

  public void setInputVariables(Set<Variable> inputVariables) {
    this.inputVariables = inputVariables;
  }

  public Variable getOutputVariable() {
    return outputVariable;
  }

  public void setOutputVariable(Variable outputVariable) {
    this.outputVariable = outputVariable;
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
