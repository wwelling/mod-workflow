package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.domain.model.AbstractBaseEntity;

@Entity
public class Task extends AbstractBaseEntity {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @NotNull
  @Size(min = 4, max = 64)
  private String delegate;

  private String script;

  private TaskScriptType scriptType;

  public Task() {
    super();
  }

  public Task(String name, Action action) {
    this();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDelegate() {
    return delegate;
  }

  public void setDelegate(String delegate) {
    this.delegate = delegate;
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

}
