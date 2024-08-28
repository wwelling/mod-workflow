package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.DirectoryAction;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.common.HasDirectoryTaskCommon;

@Entity
public class DirectoryTask extends AbstractTask implements DelegateTask, HasDirectoryTaskCommon {

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String path;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String workflow;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DirectoryAction action;

  public DirectoryTask() {
    super();

    path = "";
    workflow = "";
    action = DirectoryAction.LIST;
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (path == null) {
      path = "";
    }

    if (workflow == null) {
      workflow = "";
    }

    if (action == null) {
      action = DirectoryAction.LIST;
    }
  }

}
