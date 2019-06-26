package org.folio.rest.model;

import javax.persistence.Entity;

@Entity
public class ExtractorTask extends Task {

  public ExtractorTask() {
    super();
    setDelegate("testStreamDelegate");
  }

}
