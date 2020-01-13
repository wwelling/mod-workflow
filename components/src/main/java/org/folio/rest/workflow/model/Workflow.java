package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.spring.domain.model.AbstractBaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Workflow extends AbstractBaseEntity {

  @Column
  private boolean active;

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @NotNull
  @Size(min = 4, max = 512)
  @Column(nullable = false)
  private String description;

  @Column(unique = true)
  private String deploymentId;

  @ElementCollection(fetch = FetchType.EAGER)
  private Set<String> processDefinitionIds;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  private Trigger startTrigger;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Task> tasks;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "workflow_initial_context", joinColumns = @JoinColumn(name = "workflow_id"))
  @MapKeyColumn(name = "context_key")
  @Column(name = "context_value")
  private Map<String, String> initialContext;

  public Workflow() {
    super();
    active = false;
    processDefinitionIds = new HashSet<String>();
    tasks = new ArrayList<Task>();
    initialContext = new HashMap<String, String>();
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public String getDeploymentId() {
    return deploymentId;
  }

  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public Set<String> getProcessDefinitionIds() {
    return processDefinitionIds;
  }

  public void setProcessDefinitionIds(Set<String> processDefinitionIds) {
    this.processDefinitionIds = processDefinitionIds;
  }

  public Trigger getStartTrigger() {
    return startTrigger;
  }

  public void setStartTrigger(Trigger startTrigger) {
    this.startTrigger = startTrigger;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Map<String, String> getInitialContext() {
    return initialContext;
  }

  public void setInitialContext(Map<String, String> initialContext) {
    this.initialContext = initialContext;
  }

}
