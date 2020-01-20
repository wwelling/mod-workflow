package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class Process {

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String script;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ScriptType scriptType;

  public Process() {
    super();
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

}
