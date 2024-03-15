package org.folio.rest.workflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Getter
@Setter
public class Request {

  @NotNull
  private String url;

  @NotNull
  private HttpMethod method;

  @NotNull
  private String contentType;

  @NotNull
  private String accept;

  private String bodyTemplate;

  private boolean iterable;

  private String iterableKey;

  private String responseKey;

  public Request() {
    super();
    contentType = MediaType.APPLICATION_JSON_VALUE;
    accept = MediaType.APPLICATION_JSON_VALUE;
    bodyTemplate = "{}";
    iterable = false;
  }

}
