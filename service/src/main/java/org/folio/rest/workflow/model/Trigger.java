package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.spring.domain.model.AbstractBaseEntity;
import org.springframework.http.HttpMethod;

@Entity
public class Trigger extends AbstractBaseEntity {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @Size(min = 0, max = 256)
  @Column(nullable = true)
  private String description;

  @NotNull
  @Size(min = 2, max = 256)
  @Column(nullable = false)
  private String pathPattern;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private HttpMethod method;

  public Trigger() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
