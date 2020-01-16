package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class ProcessorTask extends Node implements Task {

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String script;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ScriptType scriptType;

  @NotNull
  @Column(nullable = false)
  private String[] contextInputKeys;

  @NotNull
  @Column(nullable = false)
  private String contextOutputKey;

  @Column(nullable = false)
  private boolean asyncBefore;

  public ProcessorTask() {
    super();
    asyncBefore = false;
  }

  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

  public ScriptType getScriptType() {
    return scriptType;
  }

  public void setScriptType(ScriptType scriptType) {
    this.scriptType = scriptType;
  }

  public String[] getContextInputKeys() {
    return contextInputKeys;
  }

  public void setContextInputKeys(String[] contextInputKeys) {
    this.contextInputKeys = contextInputKeys;
  }

  public String getContextOutputKey() {
    return contextOutputKey;
  }

  public void setContextOutputKey(String contextOutputKey) {
    this.contextOutputKey = contextOutputKey;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
