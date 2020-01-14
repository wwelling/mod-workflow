package org.folio.rest.workflow.model;

import javax.persistence.Entity;

@Entity
public class MoveToLastGateway extends Node implements Branch {

  public MoveToLastGateway() {
    super();
  }

}
