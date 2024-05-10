package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.HasAsync;
import org.folio.rest.workflow.model.has.common.HasEmailTaskCommon;
import org.springframework.lang.NonNull;

@Entity
public class EmailTask extends Node implements DelegateTask, HasAsync, HasEmailTaskCommon {

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
  @NonNull
  @Size(min = 3, max = 256)
  @Column(nullable = false)
  private String mailTo;

  @Getter
  @Setter
  @Column(nullable = true)
  private String mailCc;

  @Getter
  @Setter
  @Column(nullable = true)
  private String mailBcc;

  @Getter
  @Setter
  @NonNull
  @Size(min = 3, max = 256)
  @Column(nullable = false)
  private String mailFrom;

  @Getter
  @Setter
  @NonNull
  @Size(min = 2, max = 256)
  @Column(nullable = false)
  private String mailSubject;

  @Getter
  @Setter
  @NonNull
  @Size(min = 2)
  @Column(columnDefinition = "TEXT", nullable = false)
  private String mailText;

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = true)
  private String mailMarkup;

  @Getter
  @Setter
  @Column(nullable = true)
  private String attachmentPath;

  @Getter
  @Setter
  @Column(nullable = true)
  private String includeAttachment;

  public EmailTask() {
    super();

    inputVariables = new HashSet<EmbeddedVariable>();
    asyncBefore = false;
    asyncAfter = false;
    mailTo = "";
    mailFrom = "";
    mailSubject = "";
    mailText = "";
  }

}
