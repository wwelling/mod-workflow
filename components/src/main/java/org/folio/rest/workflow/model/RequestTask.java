package org.folio.rest.workflow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.common.HasRequestTaskCommon;

@Entity
public class RequestTask extends AbstractTask implements DelegateTask, HasRequestTaskCommon {

  @Getter
  @Setter
  @ElementCollection
  private Set<EmbeddedVariable> headerOutputVariables;

  @Getter
  @Setter
  @Embedded
  private EmbeddedRequest request;

  public RequestTask() {
    super();

    headerOutputVariables = new HashSet<>();
  }

}
