package org.folio.rest.workflow.model;

import org.folio.rest.workflow.model.enums.HttpMethod;
import org.springframework.http.MediaType;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class EmbeddedRequest {

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

  @Column(columnDefinition = "TEXT", nullable = false)
  private String bodyTemplate;

  private boolean iterable;

  private String iterableKey;

  private String responseKey;

  public EmbeddedRequest() {
    super();
    contentType = MediaType.APPLICATION_JSON_VALUE;
    accept = MediaType.APPLICATION_JSON_VALUE;
    bodyTemplate = "{}";
    iterable = false;
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

  public boolean isIterable() {
    return iterable;
  }

  public void setIterable(boolean iterable) {
    this.iterable = iterable;
  }

  public String getIterableKey() {
    return iterableKey;
  }

  public void setIterableKey(String iterableKey) {
    this.iterableKey = iterableKey;
  }

  public String getResponseKey() {
    return responseKey;
  }

  public void setResponseKey(String responseKey) {
    this.responseKey = responseKey;
  }

}
