package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.HttpMethod;
import org.folio.rest.workflow.has.common.HasActionCommon;
import org.folio.rest.workflow.model.has.HasMethod;
import org.folio.rest.workflow.model.has.HasPathPattern;

@Embeddable
public class Action implements HasActionCommon, HasMethod, HasPathPattern {

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String interfaceName;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  private String pathPattern;

  @Getter
  @Setter
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

}
