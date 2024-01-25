package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.has.common.HasActionCommon;
import org.folio.rest.workflow.model.has.HasMethod;
import org.folio.rest.workflow.model.has.HasPathPattern;
import org.springframework.http.HttpMethod;

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
