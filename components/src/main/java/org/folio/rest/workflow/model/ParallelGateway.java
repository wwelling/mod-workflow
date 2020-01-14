package org.folio.rest.workflow.model;

import javax.persistence.Entity;

@Entity
public class ParallelGateway extends Node implements Branch {

  public ParallelGateway() {
    super();
  }

}
