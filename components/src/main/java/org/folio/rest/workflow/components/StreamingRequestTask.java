package org.folio.rest.workflow.components;

import javax.persistence.Entity;

import org.springframework.http.MediaType;

@Entity
public class StreamingRequestTask extends Task {

  String storageDestination;

  String contentType;

  String accept;

  public StreamingRequestTask() {
    super();
    setDelegate("streamingRequestDelegate");
    this.contentType = MediaType.APPLICATION_JSON_VALUE;
    this.accept = MediaType.APPLICATION_JSON_VALUE;
  }

  public String getStorageDestination() {
    return storageDestination;
  }

  public void setStorageDestination(String storageDestination) {
    this.storageDestination = storageDestination;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getAccept() {
    return accept;
  }

  public void setAccept(String accept) {
    this.accept = accept;
  }

}