package org.folio.rest.workflow.components;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FileReadTask extends AbstractFileTask {

  @Column(nullable = false)
  private Long delay;

  public FileReadTask() {
    super();
    setDelegate("fileReadDelegate");
    setDelay(0L);
  }

  public FileReadTask(String name) {
    this();
    setName(name);
  }

  public Long getDelay() {
    return delay;
  }

  public void setDelay(Long delay) {
    this.delay = delay;
  }

}
