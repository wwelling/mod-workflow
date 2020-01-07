package org.folio.rest.workflow.components;

import javax.persistence.Entity;

@Entity
public class StreamingFileWriteTask extends AbstractFileTask {

  public StreamingFileWriteTask() {
    super();
    setDelegate("fileWriteDelegate");
    setStreaming(true);
  }

  public StreamingFileWriteTask(String name) {
    this();
    setName(name);
  }

}
