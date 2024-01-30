package org.folio.rest.workflow.model;

import org.folio.rest.workflow.model.enums.HttpMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Action {

  @NotNull
  @Column(nullable = false)
  private String interfaceName;

  @NotNull
  @Column(nullable = false)
  private String pathPattern;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private HttpMethod method;

  public Action() {
    super();
  }

  public Action(String interfaceName, String pathPattern, HttpMethod method) {
    this();
    this.interfaceName = interfaceName;
    this.pathPattern = pathPattern;
    this.method = method;

  }

  public String getInterfaceName() {
    return interfaceName;
  }

  public void setInterfaceName(String interfaceName) {
    this.interfaceName = interfaceName;
  }

  public String getPathPattern() {
    return pathPattern;
  }

  public void setPathPattern(String pathPattern) {
    this.pathPattern = pathPattern;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setMethod(HttpMethod method) {
    this.method = method;
  }

}
