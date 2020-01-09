package org.folio.rest.workflow.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ProcessorTask extends Task {

  @Column(columnDefinition = "TEXT")
  private String script;

  @Enumerated(EnumType.STRING)
  private TaskScriptType scriptType;

  @ElementCollection
  private List<String> contextProperties;

  public ProcessorTask() {
    super();
    this.setDelegate("streamingProcessDelegate");
    this.contextProperties = new ArrayList<String>();
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

  public List<String> getContextProperties() {
    return contextProperties;
  }

  public void setContextProperties(List<String> contextProperties) {
    this.contextProperties = contextProperties;
  }

}
