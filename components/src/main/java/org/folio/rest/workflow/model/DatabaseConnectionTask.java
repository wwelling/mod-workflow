package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.has.HasDesignation;
import org.folio.rest.workflow.model.has.HasPassword;
import org.folio.rest.workflow.model.has.HasUrl;
import org.folio.rest.workflow.model.has.HasUsername;

@Entity
public class DatabaseConnectionTask extends AbstractDelegateTaskNode implements HasDesignation, HasPassword, HasUrl, HasUsername {

  @Getter
  @Setter
  @Column(nullable = false)
  private String designation;

  @Getter
  @Setter
  @Column(nullable = true)
  private String password;

  @Getter
  @Setter
  @Column(nullable = false)
  private String url;

  @Getter
  @Setter
  @Column(nullable = true)
  private String username;

  public DatabaseConnectionTask() {
    super();

    designation = "";
    url = "";
  }

  @PrePersist
  public void prePersist() {
    if (designation == null) {
      designation = "";
    }

    if (url == null) {
      url = "";
    }
  }

}
