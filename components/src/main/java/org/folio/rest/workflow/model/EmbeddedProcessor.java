package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class EmbeddedProcessor {

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ScriptType scriptType;

  @NotNull
  @Size(min = 4, max = 128)
  @Column(nullable = false)
  private String functionName;

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String code;

  @Column(nullable = false)
  private int buffer;

  @Column(nullable = false)
  private int delay;

  public EmbeddedProcessor() {
    super();
    buffer = 0;
    delay = 0;
  }

  public ScriptType getScriptType() {
    return scriptType;
  }

  public void setScriptType(ScriptType scriptType) {
    this.scriptType = scriptType;
  }

  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getBuffer() {
    return buffer;
  }

  public void setBuffer(int buffer) {
    this.buffer = buffer;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

}
