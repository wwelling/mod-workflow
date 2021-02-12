package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.workflow.model.converter.JsonNodeConverter;
import org.folio.spring.domain.model.AbstractBaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Workflow extends AbstractBaseEntity {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @NotNull
  @Size(min = 1, max = 64)
  @Column(nullable = false)
  private String versionTag;

  @Min(0)
  @Column(nullable = false)
  private Integer historyTimeToLive;

  @Size(min = 0, max = 512)
  @Column(nullable = true)
  private String description;

  @Column(nullable = false)
  private boolean active;

  @Column(unique = true)
  private String deploymentId;

  @Embedded
  private Setup setup;

  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  @ElementCollection
  @CollectionTable(name = "workflow_initial_context", joinColumns = @JoinColumn(name = "workflow_id"))
  @MapKeyColumn(name = "context_key")
  @Column(name = "context_value")
  @Convert(converter = JsonNodeConverter.class, attributeName = "value")
  private Map<String, JsonNode> initialContext;

  public Workflow() {
    super();
    active = false;
    nodes = new ArrayList<Node>();
    initialContext = new HashMap<String, JsonNode>();
    historyTimeToLive = 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersionTag() {
    return versionTag;
  }

  public void setVersionTag(String versionTag) {
    this.versionTag = versionTag;
  }

  public Integer getHistoryTimeToLive() {
    return historyTimeToLive;
  }

  public void setHistoryTimeToLive(Integer historyTimeToLive) {
    this.historyTimeToLive = historyTimeToLive;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getDeploymentId() {
    return deploymentId;
  }

  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public Setup getSetup() {
    return setup;
  }

  public void setSetup(Setup setup) {
    this.setup = setup;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public Map<String, JsonNode> getInitialContext() {
    return initialContext;
  }

  public void setInitialContext(Map<String, JsonNode> initialContext) {
    this.initialContext = initialContext;
  }

}
