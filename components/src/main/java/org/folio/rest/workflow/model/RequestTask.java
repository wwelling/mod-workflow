package org.folio.rest.workflow.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.annotation.Expression;
import org.springframework.http.HttpMethod;

@Entity
public class RequestTask extends Task {

  @Expression
  @NotNull
  @Column(nullable = false)
  private String url;

  @Expression
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private HttpMethod method;

  @Expression
  @NotNull
  @Column(nullable = false)
  private String contentType;

  @Expression
  @NotNull
  @Column(nullable = false)
  private String accept;

  @Expression
  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String bodyTemplate;

  @Expression
  @ElementCollection
  private Set<String> contextRequestKeys;

  @Expression
  @NotNull
  @Column(nullable = false)
  private String contextResponseKey;

  public RequestTask() {
    super();
    this.contextRequestKeys = new HashSet<String>();
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

  @Override
  public String id(int index) {
    return String.format("request_task_%s", index);
  }

}
