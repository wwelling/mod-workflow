package org.folio.rest.workflow.model;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class EmbeddedVariable {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(name = "vkey", nullable = true)
  private String key;

  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  private VariableType type;

  @Column(nullable = true)
  private boolean spin;

  @Column(nullable = true)
  private Boolean asJson;

  @Column(nullable = true)
  private Boolean asTransient;

  public EmbeddedVariable() {
    type = VariableType.PROCESS;
    spin = false;
    asJson = false;
    asTransient = false;
  }

  public Optional<String> getKey() {
    return Optional.ofNullable(key);
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Optional<VariableType> getType() {
    return Optional.ofNullable(type);
  }

  public void setType(VariableType type) {
    this.type = type;
  }

  public boolean isSpin() {
    return spin;
  }

  public void setSpin(boolean spin) {
    this.spin = spin;
  }

  public Boolean getAsJson() {
    return asJson;
  }

  public void setAsJson(Boolean asJson) {
    this.asJson = asJson;
  }

  public Boolean getAsTransient() {
    return asTransient;
  }

  public void setAsTransient(Boolean asTransient) {
    this.asTransient = asTransient;
  }

}
