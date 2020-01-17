package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

  @ElementCollection
  private Set<String> contextInputKeys;

  @ElementCollection
  private Set<String> contextCacheInputKeys;

  @NotNull
  @Column(nullable = false)
  private String outputKey;

  @Column(nullable = false)
  private Boolean useCacheOutput;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public ProcessorTask() {
    super();
    contextInputKeys = new HashSet<String>();
    contextCacheInputKeys = new HashSet<String>();
    useCacheOutput = false;
    asyncBefore = false;
    asyncAfter = false;
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

  public Set<String> getContextInputKeys() {
    return contextInputKeys;
  }

  public void setContextInputKeys(Set<String> contextInputKeys) {
    this.contextInputKeys = contextInputKeys;
  }

  public Set<String> getContextCacheInputKeys() {
    return contextCacheInputKeys;
  }

  public void setContextCacheInputKeys(Set<String> contextCacheInputKeys) {
    this.contextCacheInputKeys = contextCacheInputKeys;
  }

  public String getOutputKey() {
    return outputKey;
  }

  public void setOutputKey(String outputKey) {
    this.outputKey = outputKey;
  }

  public Boolean getUseCacheOutput() {
    return useCacheOutput;
  }

  public void setUseCacheOutput(Boolean useCacheOutput) {
    this.useCacheOutput = useCacheOutput;
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
