package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.HttpMethod;
import org.folio.rest.workflow.model.has.common.HasEmbeddedRequestCommon;
import org.springframework.http.MediaType;

@Embeddable
public class EmbeddedRequest implements HasEmbeddedRequestCommon {

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String accept;

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = false)
  private String bodyTemplate;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String contentType;

  @Getter
  @Setter
  private boolean iterable;

  @Getter
  @Setter
  private String iterableKey;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private HttpMethod method;

  @Getter
  @Setter
  private String responseKey;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String url;

  public EmbeddedRequest() {
    super();

    accept = MediaType.APPLICATION_JSON_VALUE;
    bodyTemplate = "{}";
    contentType = MediaType.APPLICATION_JSON_VALUE;
    iterable = false;
    method = HttpMethod.GET;
    url = "";
  }

  @PrePersist
  public void prePersist() {
    if (accept == null) {
      accept = MediaType.APPLICATION_JSON_VALUE;
    }

    if (bodyTemplate == null) {
      bodyTemplate = "{}";
    }

    if (contentType == null) {
      contentType = MediaType.APPLICATION_JSON_VALUE;
    }

    if (method == null) {
      method = HttpMethod.GET;
    }

    if (url == null) {
      url = "";
    }
  }

}
