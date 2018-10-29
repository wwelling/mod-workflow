package org.folio.rest.jms;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.databind.JsonNode;

public class Event {

  private final String tenant;

  private final String path;

	private final HttpMethod method;

	private JsonNode body;

  private final HttpHeaders headers;

	public Event(String tenant, String path, HttpMethod method, HttpHeaders headers) {
		this(tenant, path, method, null, headers);
	}

	public Event(String tenant, String path, HttpMethod method, JsonNode body, HttpHeaders headers) {
    this.tenant = tenant;
    this.path = path;
		this.method = method;
    this.body = body;
    this.headers = headers;
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
