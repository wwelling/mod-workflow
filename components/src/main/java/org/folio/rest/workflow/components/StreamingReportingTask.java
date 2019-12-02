package org.folio.rest.workflow.components;

import javax.persistence.Entity;

@Entity
public class StreamingReportingTask extends Task {

  public StreamingReportingTask()  {
    super();
    this.setDelegate("streamingReportingDelegate");
  }

  public StreamingReportingTask(String name) {
    this();
    setName(name);
    setStreaming(true);
  }
}
