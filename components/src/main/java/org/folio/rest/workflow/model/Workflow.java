package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.spring.domain.model.AbstractBaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Workflow extends AbstractBaseEntity {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @NotNull
  @Size(min = 1, max = 64)
  @Column(unique = true)
  private String versionTag;

  @Min(0)
  @Column(nullable = false)
  private Integer historyTimeToLive;

  @NotNull
  @Size(min = 4, max = 512)
  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private boolean active;

  @Column(unique = true)
  private String deploymentId;

  @ManyToMany
  private List<Node> nodes;

  @ElementCollection
  @CollectionTable(name = "workflow_initial_context", joinColumns = @JoinColumn(name = "workflow_id"))
  @MapKeyColumn(name = "context_key")
  @Column(name = "context_value")
  private Map<String, String> initialContext;

  public Workflow() {
    super();
    active = false;
    nodes = new ArrayList<Node>();
    initialContext = new HashMap<String, String>();
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

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public Map<String, String> getInitialContext() {
    return initialContext;
  }

  public void setInitialContext(Map<String, String> initialContext) {
    this.initialContext = initialContext;
  }

}
