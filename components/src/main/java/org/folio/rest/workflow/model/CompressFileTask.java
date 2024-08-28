package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.CompressFileContainer;
import org.folio.rest.workflow.enums.CompressFileFormat;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.common.HasCompressFileTaskCommon;

@Entity
public class CompressFileTask extends AbstractTask implements DelegateTask, HasCompressFileTaskCommon {

  @Getter
  @Setter
  @Column(nullable = false)
  private String source;

  @Getter
  @Setter
  @Column(nullable = false)
  private String destination;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CompressFileFormat format;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CompressFileContainer container;

  public CompressFileTask() {
    super();

    source = "";
    destination = "";
    format = CompressFileFormat.ZIP;
    container = CompressFileContainer.NONE;
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (source == null) {
      source = "";
    }

    if (destination == null) {
      destination = "";
    }

    if (format == null) {
      format = CompressFileFormat.ZIP;
    }

    if (container == null) {
      container = CompressFileContainer.NONE;
    }
  }

}
