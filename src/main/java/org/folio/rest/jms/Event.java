package org.folio.rest.jms;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;

public class Event implements Serializable {

  private static final long serialVersionUID = 4215503126159461393L;

  private final String tenant;

  private final Object body;

  private final HttpHeaders headers;

  public Event(String tenant, Object body, HttpHeaders headers) {
    this.tenant = tenant;
    this.body = body;
    this.headers = headers;
  }

  public String getTenant() {
    return tenant;
  }

  public Object getBody() {
    return body;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

}
