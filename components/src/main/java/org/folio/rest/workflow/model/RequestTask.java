package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.folio.rest.workflow.components.Task;

@Entity
public class RequestTask extends Node implements Task {

  @Embedded
  private EmbeddedRequest request;

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @ElementCollection
  private Set<EmbeddedVariable> headerOutputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public RequestTask() {
    super();
    inputVariables = new HashSet<EmbeddedVariable>();
    headerOutputVariables = new HashSet<EmbeddedVariable>();
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

  public Set<EmbeddedVariable> getHeaderOutputVariables() {
    return headerOutputVariables;
  }

  public void setHeaderOutputVariables(Set<EmbeddedVariable> headerOutputVariables) {
    this.headerOutputVariables = headerOutputVariables;
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
