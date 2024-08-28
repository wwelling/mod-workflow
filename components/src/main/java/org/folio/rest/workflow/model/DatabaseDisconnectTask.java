package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasDesignation;

@Entity
public class DatabaseDisconnectTask extends AbstractDelegateTaskNode implements HasDesignation {

  @Getter
  @Setter
  @Column(nullable = false)
  private String designation;

  public DatabaseDisconnectTask() {
    super();

    designation = "";
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (designation == null) {
      designation = "";
    }
  }

}
