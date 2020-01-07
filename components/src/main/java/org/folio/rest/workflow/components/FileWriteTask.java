package org.folio.rest.workflow.components;

import javax.persistence.Entity;

@Entity
public class FileWriteTask extends AbstractFileTask {

  public FileWriteTask() {
    super();
    setDelegate("fileWriteDelegate");
  }

  public FileWriteTask(String name) {
    this();
    setName(name);
  }

}
