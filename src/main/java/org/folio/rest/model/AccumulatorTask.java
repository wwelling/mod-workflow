package org.folio.rest.model;

import javax.persistence.Entity;

@Entity
public class AccumulatorTask extends Task {

  Long acumulateTo;

  Long delayDuration;

  public AccumulatorTask()  {
    super();
    this.setDelegate("testAccumulatorDelegate");
  }

  public AccumulatorTask(String name) {
    this();
    setName(name);
  }

  public Long getAcumulateTo() {
    return acumulateTo;
  }

  public void setAcumulateTo(Long acumulateTo) {
    this.acumulateTo = acumulateTo;
  }

  public Long getDelayDuration() {
    return delayDuration;
  }

  public void setDelayDuration(Long delayDuration) {
    this.delayDuration = delayDuration;
  }

}
