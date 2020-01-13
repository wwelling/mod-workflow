package org.folio.rest.workflow.model;

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
  private String[] contextInputKeys;

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

  @Override
  public String id(int index) {
    return String.format("process_task_%s", index);
  }

}
