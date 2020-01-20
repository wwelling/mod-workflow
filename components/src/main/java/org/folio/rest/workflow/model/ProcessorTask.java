package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.components.Task;

@Entity
public class ProcessorTask extends Node implements Task {

  @NotNull
  @Embedded
  private Process process;

  @ElementCollection
  private Set<Variable> inputVariables;

  @Embedded
  private Variable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public ProcessorTask() {
    super();
    inputVariables = new HashSet<Variable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  public Process getProcess() {
    return process;
  }

  public void setProcess(Process process) {
    this.process = process;
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
