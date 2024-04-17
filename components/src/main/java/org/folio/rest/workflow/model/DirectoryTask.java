package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.DirectoryAction;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.HasAsync;
import org.folio.rest.workflow.model.has.common.HasDirectoryTaskCommon;

@Entity
public class DirectoryTask extends Node implements DelegateTask, HasAsync, HasDirectoryTaskCommon {

  @Getter
  @Setter
  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Getter
  @Setter
  @Embedded
  private EmbeddedVariable outputVariable;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncBefore;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean asyncAfter;

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

    inputVariables = new HashSet<EmbeddedVariable>();
    asyncBefore = false;
    asyncAfter = false;
  }

}
