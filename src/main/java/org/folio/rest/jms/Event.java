package org.folio.rest.jms;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.databind.JsonNode;

public class Event {

  private final String name;

  private final String description;

  private final String tenant;

  private final String path;

  private final HttpMethod method;

  private JsonNode body;

  private final HttpHeaders headers;

  public Event(String name, String description, String tenant, String path, HttpMethod method, JsonNode body,
      HttpHeaders headers) {
    this.name = name;
    this.description = description;
    this.tenant = tenant;
    this.path = path;
    this.method = method;
    this.body = body;
    this.headers = headers;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getTenant() {
    return tenant;
  }

  public String getPath() {
    return path;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setBody(JsonNode body) {
    this.body = body;
  }

  public JsonNode getBody() {
    return body;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

}
