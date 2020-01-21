package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.folio.rest.workflow.components.Task;

@Entity
public class StreamingExtractTransformLoadTask extends Node implements Task {

  @ManyToMany
  private List<Extractor> extractors;

  @ElementCollection
  private List<Processor> processors;

  @ElementCollection
  private List<Request> requests;

  @ElementCollection
  private Set<Variable> inputVariables;

  @Embedded
  private Variable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public StreamingExtractTransformLoadTask() {
    extractors = new ArrayList<Extractor>();
    processors = new ArrayList<Processor>();
    requests = new ArrayList<Request>();
    inputVariables = new HashSet<Variable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  public List<Extractor> getExtractors() {
    return extractors;
  }

  public void setExtractors(List<Extractor> extractors) {
    this.extractors = extractors;
  }

  public List<Processor> getProcessors() {
    return processors;
  }

  public void setProcessors(List<Processor> scripts) {
    this.processors = scripts;
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void setRequests(List<Request> requests) {
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
