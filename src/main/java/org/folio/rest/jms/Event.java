package org.folio.rest.jms;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.JsonNode;

public class Event {

  private final String tenant;

  private final String path;

  private final JsonNode body;

  private final HttpHeaders headers;

  public Event(String tenant, String path, JsonNode body, HttpHeaders headers) {
    this.tenant = tenant;
    this.path = path;
    this.body = body;
    this.headers = headers;
  }

  public String getTenant() {
    return tenant;
  }

  public String getPath() {
    return path;
  }

  public JsonNode getBody() {
    return body;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

}
