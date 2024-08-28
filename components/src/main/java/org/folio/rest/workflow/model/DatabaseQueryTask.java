package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.DatabaseResultType;
import org.folio.rest.workflow.model.has.HasDesignation;
import org.folio.rest.workflow.model.has.common.HasDatabaseQueryTaskCommon;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class DatabaseQueryTask extends AbstractDelegateTaskNode implements HasDatabaseQueryTaskCommon, HasDesignation {

  @Getter
  @Setter
  @Column(nullable = false)
  private String designation;

  @Getter
  @Setter
  @Column(nullable = true)
  private String outputPath;

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = false)
  private String query;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private DatabaseResultType resultType;

  @Getter
  @Setter
  @Column(nullable = true)
  @ColumnDefault("false")
  private Boolean includeHeader;

  public DatabaseQueryTask() {
    super();

    designation = "";
    query = "";
    includeHeader = false;
  }

  @PrePersist
  public void prePersist() {
    if (designation == null) {
      designation = "";
    }

    if (query == null) {
      query = "";
    }

    if (includeHeader == null) {
      includeHeader = false;
    }
  }

}
