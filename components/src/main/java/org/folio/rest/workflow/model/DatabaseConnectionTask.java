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
import org.folio.rest.workflow.model.has.HasPassword;
import org.folio.rest.workflow.model.has.HasUrl;
import org.folio.rest.workflow.model.has.HasUsername;

@Entity
public class DatabaseConnectionTask extends Node implements DelegateTask, HasDesignation, HasPassword, HasUrl, HasUsername {

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

  @Getter
  @Setter
  @Column(nullable = false)
  private String url;

  @Getter
  @Setter
  @Column(nullable = true)
  private String username;

  @Getter
  @Setter
  @Column(nullable = true)
  private String password;

  public DatabaseConnectionTask() {
    super();

    asyncBefore = false;
    asyncAfter = false;
  }

}
