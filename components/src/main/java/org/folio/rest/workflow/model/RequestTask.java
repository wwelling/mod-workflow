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
  private Set<String> contextInputKeys;

  @ElementCollection
  private Set<String> contextCacheInputKeys;

  @NotNull
  @Column(nullable = false)
  private String outputKey;

  @Column(nullable = false)
  private Boolean useCacheOutput;

  @Column(nullable = false)
  private boolean asyncBefore;

  @Column(nullable = false)
  private boolean asyncAfter;

  public RequestTask() {
    super();
    contentType = MediaType.APPLICATION_JSON_VALUE;
    accept = MediaType.APPLICATION_JSON_VALUE;
    contextInputKeys = new HashSet<String>();
    contextCacheInputKeys = new HashSet<String>();
    useCacheOutput = false;
    asyncBefore = false;
    asyncAfter = false;
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

  public Set<String> getContextInputKeys() {
    return contextInputKeys;
  }

  public void setContextInputKeys(Set<String> contextInputKeys) {
    this.contextInputKeys = contextInputKeys;
  }

  public Set<String> getContextCacheInputKeys() {
    return contextCacheInputKeys;
  }

  public void setContextCacheInputKeys(Set<String> contextCacheInputKeys) {
    this.contextCacheInputKeys = contextCacheInputKeys;
  }

  public String getOutputKey() {
    return outputKey;
  }

  public void setOutputKey(String outputKey) {
    this.outputKey = outputKey;
  }

  public Boolean getUseCacheOutput() {
    return useCacheOutput;
  }

  public void setUseCacheOutput(Boolean useCacheOutput) {
    this.useCacheOutput = useCacheOutput;
  }

  public boolean isAsyncBefore() {
    return asyncBefore;
  }

  public void setAsyncBefore(boolean asyncBefore) {
    this.asyncBefore = asyncBefore;
  }

  public boolean isAsyncAfter() {
    return asyncAfter;
  }

  public void setAsyncAfter(boolean asyncAfter) {
    this.asyncAfter = asyncAfter;
  }

}
