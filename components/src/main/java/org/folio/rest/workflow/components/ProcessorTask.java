package org.folio.rest.workflow.components;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class ProcessorTask extends Task {

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String script;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TaskScriptType scriptType;

  @NotNull
  @Column(nullable = false)
  private String contextInputKey;

  @NotNull
  @Column(nullable = false)
  private String contextOutputKey;

  public ProcessorTask() {
    super();
  }

  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

  public TaskScriptType getScriptType() {
    return scriptType;
  }

  public void setScriptType(TaskScriptType scriptType) {
    this.scriptType = scriptType;
  }

  public String getContextInputKey() {
    return contextInputKey;
  }

  public void setContextInputKey(String contextInputKey) {
    this.contextInputKey = contextInputKey;
  }

  public String getContextOutputKey() {
    return contextOutputKey;
  }

  public void setContextOutputKey(String contextOutputKey) {
    this.contextOutputKey = contextOutputKey;
  }

}
