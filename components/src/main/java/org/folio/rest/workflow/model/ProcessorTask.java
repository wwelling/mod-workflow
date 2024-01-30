package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import org.folio.rest.workflow.model.components.DelegateTask;

@Entity
public class ProcessorTask extends Node implements DelegateTask {

  @Embedded
  private EmbeddedProcessor processor;

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public ProcessorTask() {
    super();
    inputVariables = new HashSet<EmbeddedVariable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  public EmbeddedProcessor getProcessor() {
    return processor;
  }

  public void setProcessor(EmbeddedProcessor processor) {
    this.processor = processor;
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
