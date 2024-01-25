package org.folio.rest.workflow.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.HasDesignation;

@Entity
public class DatabaseDisconnectTask extends Node implements DelegateTask, HasDesignation {

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
  @Column(nullable = false)
  private String designation;

  public DatabaseDisconnectTask() {
    super();

    asyncBefore = false;
    asyncAfter = false;
  }

}
