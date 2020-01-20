package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.workflow.components.Conditional;
import org.folio.rest.workflow.components.Gateway;

@Entity
public class ConditionalGateway extends Node implements Gateway, Conditional {

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ConditionalGatewayType type;

  @NotNull
  @Size(min = 4, max = 128)
  @Column(nullable = false)
  private String condition;

  @NotNull
  @Size(min = 2, max = 64)
  @Column(nullable = false)
  private String answer;

  @ManyToMany
  private List<Node> nodes;

  public ConditionalGateway() {
    super();
    nodes = new ArrayList<Node>();
  }

  public ConditionalGatewayType getType() {
    return type;
  }

  public void setType(ConditionalGatewayType type) {
    this.type = type;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

}
