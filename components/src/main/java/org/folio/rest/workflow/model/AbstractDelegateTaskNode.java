package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.converter.EmbeddedVariableConverter;
import org.hibernate.annotations.ColumnDefault;

/**
 * Provides a superclass for any Node implementing a DelegateTask.
 *
 * This is intended to reduce repitition of getters and setters needed by the DelegateTask.
 */
@MappedSuperclass
public abstract class AbstractDelegateTaskNode extends Node implements DelegateTask {

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean asyncAfter;

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean asyncBefore;

  @Getter
  @Setter
  @ElementCollection
  private Set<EmbeddedVariable> inputVariables;

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = true)
  @Convert(converter = EmbeddedVariableConverter.class)
  private EmbeddedVariable outputVariable;

  AbstractDelegateTaskNode() {
    super();

    asyncAfter = false;
    asyncBefore = false;
    inputVariables = new HashSet<EmbeddedVariable>();
  }

  @PrePersist
  public void prePersist() {
    if (asyncAfter == null) {
      asyncAfter = false;
    }

    if (asyncBefore == null) {
      asyncBefore = false;
    }

    if (asyncBefore == null) {
      asyncBefore = false;
    }

    if (inputVariables == null) {
      inputVariables = new HashSet<EmbeddedVariable>();
    }
  }

}
