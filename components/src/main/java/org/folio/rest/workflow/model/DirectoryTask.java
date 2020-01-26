package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.model.components.Task;

@Entity
public class DirectoryTask extends Node implements Task {

  @NotNull
  @Column(nullable = false)
  private String path;

  @NotNull
  @Column(nullable = false)
  private String workflow;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DirectoryAction action;

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public DirectoryTask() {
    super();
    inputVariables = new HashSet<EmbeddedVariable>();
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

  public DirectoryAction getAction() {
    return action;
  }

  public void setAction(DirectoryAction action) {
    this.action = action;
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
