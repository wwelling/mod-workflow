package org.folio.rest.workflow.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.folio.rest.workflow.model.components.DelegateTask;

@Entity
public class DatabaseQueryTask extends Node implements DelegateTask {

  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Embedded
  private EmbeddedVariable outputVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  @Column(nullable = false)
  private String designation;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String query;

  @Column(nullable = true)
  private String outputPath;

  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private DatabaseResultType resultType;

  @Column(nullable = false)
  private Boolean includeHeader;

  public DatabaseQueryTask() {
    super();
    asyncBefore = false;
    asyncAfter = false;
    resultType = DatabaseResultType.TSV;
    includeHeader = false;
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

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getOutputPath() {
    return outputPath;
  }

  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath;
  }

  public DatabaseResultType getResultType() {
    return resultType;
  }

  public void setResultType(DatabaseResultType resultType) {
    this.resultType = resultType;
  }

  public Boolean isIncludeHeader() {
    return includeHeader;
  }

  public void setIncludeHeader(Boolean includeHeader) {
    this.includeHeader = includeHeader;
  }

}
