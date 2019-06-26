package org.folio.rest.model;

import javax.persistence.Entity;

@Entity
public class AccumulatorTask extends Task {

  public AccumulatorTask()  {
    super();
    this.setDelegate("testAccumulatorDelegate");
  }

  public AccumulatorTask(String name) {
    this();
    setName(name);
  }

}
