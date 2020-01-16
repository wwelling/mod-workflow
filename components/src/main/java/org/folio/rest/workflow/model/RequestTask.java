package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Entity
public class RequestTask extends Node implements Task {

  @NotNull
  @Column(nullable = false)
  private String url;

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

  @ElementCollection
  private Set<String> contextRequestKeys;

  @NotNull
  @Column(nullable = false)
  private String contextResponseKey;

  @Column(nullable = false)
  private boolean asyncBefore;

  public RequestTask() {
    super();
    contextRequestKeys = new HashSet<String>();
    contentType = MediaType.APPLICATION_JSON_VALUE;
    accept = MediaType.APPLICATION_JSON_VALUE;
    asyncBefore = false;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public Set<String> getContextRequestKeys() {
    return contextRequestKeys;
  }

  public void setContextRequestKeys(Set<String> contextRequestKeys) {
    this.contextRequestKeys = contextRequestKeys;
  }

  public String getContextResponseKey() {
    return contextResponseKey;
  }

  public void setContextResponseKey(String contextResponseKey) {
    this.contextResponseKey = contextResponseKey;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

}
