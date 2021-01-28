package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.model.components.Task;

@Entity
public class ScriptTask extends Node implements Task {

  @Column(nullable = false)
  private String scriptFormat;

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String code;

  @Column(nullable = true)
  private String resultVariable;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public ScriptTask() {
    super();
    scriptFormat = "javaScript";
    asyncBefore = false;
    asyncAfter = false;
  }

  public String getScriptFormat() {
    return scriptFormat;
  }

  public void setScriptFormat(String scriptFormat) {
    this.scriptFormat = scriptFormat;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getResultVariable() {
    return resultVariable;
  }

  public void setResultVariable(String resultVariable) {
    this.resultVariable = resultVariable;
  }

  public boolean hasResultVariable() {
    return resultVariable != null;
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
