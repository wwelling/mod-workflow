package org.folio.rest.workflow.components;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;

@Entity
public class RequestTask extends Task {

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private HttpMethod method;

  @NotNull
  @Column(nullable = false)
  private String contentType;

  @NotNull
  @Column(nullable = false)
  private String accept;

  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String bodyTemplate;

  @NotNull
  @Column(nullable = false)
  private String contextRequestKey;

  @NotNull
  @Column(nullable = false)
  private String contextResponseKey;

  public RequestTask() {
    super();
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setMethod(HttpMethod method) {
    this.method = method;
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

  public String getBodyTemplate() {
    return bodyTemplate;
  }

  public void setBodyTemplate(String bodyTemplate) {
    this.bodyTemplate = bodyTemplate;
  }

  public String getContextRequestKey() {
    return contextRequestKey;
  }

  public void setContextRequestKey(String contextRequestKey) {
    this.contextRequestKey = contextRequestKey;
  }

  public String getContextResponseKey() {
    return contextResponseKey;
  }

  public void setContextResponseKey(String contextResponseKey) {
    this.contextResponseKey = contextResponseKey;
  }

}
