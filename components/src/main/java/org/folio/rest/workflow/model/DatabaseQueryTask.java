package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.DatabaseResultType;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.HasDesignation;
import org.folio.rest.workflow.model.has.common.HasDatabaseQueryTaskCommon;

@Entity
public class DatabaseQueryTask extends Node implements DelegateTask, HasDatabaseQueryTaskCommon, HasDesignation {

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
  @Column(columnDefinition = "TEXT", nullable = false)
  private String query;

  @Getter
  @Setter
  @Column(nullable = true)
  private String outputPath;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private DatabaseResultType resultType;

  @Getter
  @Setter
  @Column(nullable = false)
  private Boolean includeHeader;

  public DatabaseQueryTask() {
    super();

    asyncBefore = false;
    asyncAfter = false;
    resultType = DatabaseResultType.TSV;
    includeHeader = false;
  }

}
