package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.Task;
import org.folio.rest.workflow.model.has.HasAsync;
import org.folio.rest.workflow.model.has.HasCode;
import org.folio.rest.workflow.model.has.common.HasScriptTaskCommon;

@Entity
public class ScriptTask extends Node implements HasCode, HasAsync, HasScriptTaskCommon, Task {

  @Getter
  @Setter
  @Column(nullable = false)
  private String scriptFormat;

  @Getter
  @Setter
  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String code;

  @Getter
  @Setter
  @Column(nullable = true)
  private String resultVariable;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncBefore;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncAfter;

  public ScriptTask() {
    super();

    scriptFormat = "javaScript";
    asyncBefore = false;
    asyncAfter = false;
  }

  public boolean hasResultVariable() {
    return resultVariable != null;
  }

}
