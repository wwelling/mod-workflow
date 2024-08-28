package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasCode;
import org.folio.rest.workflow.model.has.common.HasScriptTaskCommon;

@Entity
public class ScriptTask extends AbstractDelegateTaskNode implements HasCode, HasScriptTaskCommon {

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
  private String scriptFormat;

  public ScriptTask() {
    super();

    code = "";
    scriptFormat = "javaScript";
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (code == null) {
      code = "";
    }

    if (scriptFormat == null) {
      scriptFormat = "javaScript";
    }
  }

  public boolean hasResultVariable() {
    return resultVariable != null;
  }

}
