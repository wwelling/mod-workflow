package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.folio.rest.workflow.model.components.DelegateTask;

@Entity
public class StreamingExtractTransformLoadTask extends Node implements DelegateTask {

  @ElementCollection
  private List<EmbeddedExtractor> extractors;

  @ElementCollection
  private List<EmbeddedProcessor> processors;

  @ElementCollection
  private List<EmbeddedRequest> requests;

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public StreamingExtractTransformLoadTask() {
    extractors = new ArrayList<EmbeddedExtractor>();
    processors = new ArrayList<EmbeddedProcessor>();
    requests = new ArrayList<EmbeddedRequest>();
    inputVariables = new HashSet<EmbeddedVariable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  public List<EmbeddedExtractor> getExtractors() {
    return extractors;
  }

  public void setExtractors(List<EmbeddedExtractor> extractors) {
    this.extractors = extractors;
  }

  public List<EmbeddedProcessor> getProcessors() {
    return processors;
  }

  public void setProcessors(List<EmbeddedProcessor> scripts) {
    this.processors = scripts;
  }

  public List<EmbeddedRequest> getRequests() {
    return requests;
  }

  public void setRequests(List<EmbeddedRequest> requests) {
    this.requests = requests;
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
