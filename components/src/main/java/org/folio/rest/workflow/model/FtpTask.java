package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.SftpOp;
import org.folio.rest.workflow.model.components.DelegateTask;
import org.folio.rest.workflow.model.has.HasPassword;
import org.folio.rest.workflow.model.has.HasService;
import org.folio.rest.workflow.model.has.HasUsername;
import org.folio.rest.workflow.model.has.common.HasFtpTaskCommon;

@Entity
public class FtpTask extends Node implements DelegateTask, HasFtpTaskCommon, HasPassword, HasService, HasUsername {

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
  private String originPath;

  @Getter
  @Setter
  @Column(nullable = false)
  private String destinationPath;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SftpOp op;

  @Getter
  @Setter
  @Column(nullable = false)
  private String scheme;

  @Getter
  @Setter
  @Column(nullable = false)
  private String host;

  @Getter
  @Setter
  @Column(nullable = false)
  private int port;

  @Getter
  @Setter
  @Column(nullable = true)
  private String username;

  @Getter
  @Setter
  @Column(nullable = true)
  private String password;

  public FtpTask() {
    super();

    inputVariables = new HashSet<EmbeddedVariable>();
    asyncBefore = false;
    asyncAfter = false;
  }

  @Override
  public String getBasePath() {
    // This is currently not used.
    return "";
  }

  @Override
  public void setBasePath(String basePath) {
    // This is currently not used.
  }

}
