package org.folio.rest.workflow.dto;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class Request {

  @NotNull
  private String url;

  @NotNull
  private HttpMethod method;

  @NotNull
  private String contentType;

  @NotNull
  private String accept;

  @NotNull
  private String bodyTemplate;

  private boolean iterate;

  public Request() {
    super();
    contentType = MediaType.APPLICATION_JSON_VALUE;
    accept = MediaType.APPLICATION_JSON_VALUE;
    iterate = false;
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

  public boolean isIterate() {
    return iterate;
  }

  public void setIterate(boolean iterate) {
    this.iterate = iterate;
  }

}
