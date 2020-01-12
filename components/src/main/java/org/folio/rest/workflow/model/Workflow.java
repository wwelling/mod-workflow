package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.spring.domain.model.AbstractBaseEntity;

@Entity
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

  @Column
  private Boolean reportingEnabled;

  @Column
  private Boolean requiresAuthentication;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  private Trigger startTrigger;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Task> tasks;

  public Workflow() {
    super();
    active = false;
    reportingEnabled = false;
    requiresAuthentication = false;
    processDefinitionIds = new HashSet<String>();
    tasks = new ArrayList<Task>();
  }

  public Workflow(String name) {
    this();
    this.name = name;
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

  public Boolean getReportingEnabled() {
    return reportingEnabled;
  }

  public void setReportingEnabled(Boolean reportingEnabled) {
    this.reportingEnabled = reportingEnabled;
  }

  public Boolean getRequiresAuthentication() {
    return requiresAuthentication;
  }

  public void setRequiresAuthentication(Boolean requiresAuthentication) {
    this.requiresAuthentication = requiresAuthentication;
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

}
