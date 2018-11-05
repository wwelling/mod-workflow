package org.folio.rest.jms;

import java.util.ArrayList;
import java.util.List;

import org.folio.rest.model.Trigger;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.JsonNode;

public class Event {

  private final Trigger trigger;

  private final String tenant;

  private final String path;

  private JsonNode body;

  private final HttpHeaders headers;

  private List<String> processDefinitionIds;

  // @formatter:off
  public Event(
    Trigger trigger,
    String tenant,
    String path,
    JsonNode body,
    HttpHeaders headers
  ) {
    // @formatter:on
    this.trigger = trigger;
    this.tenant = tenant;
    this.path = path;
    this.body = body;
    this.headers = headers;
    this.processDefinitionIds = new ArrayList<String>();
  }

  public Trigger getTrigger() {
    return trigger;
  }

  public String getTenant() {
    return tenant;
  }

  public String getPath() {
    return path;
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

  public List<String> getProcessDefinitionIds() {
    return processDefinitionIds;
  }

  public void setProcessDefinitionIds(List<String> processDefinitionIds) {
    this.processDefinitionIds = processDefinitionIds;
  }

}
