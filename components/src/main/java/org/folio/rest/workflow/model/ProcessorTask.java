package org.folio.rest.workflow.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.common.HasProcessorTaskCommon;

@Entity
public class ProcessorTask extends AbstractDelegateTaskNode implements HasProcessorTaskCommon {

  @Getter
  @Setter
  @Embedded
  private EmbeddedProcessor processor;

  public ProcessorTask() {
    super();
  }

}
