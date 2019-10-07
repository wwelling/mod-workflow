package org.folio.rest.workflow.model.repo;

import java.util.List;

import org.folio.rest.workflow.components.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkflowRepo extends JpaRepository<Workflow, String> {

  public List<Workflow> findByStartTriggerId(String id);

}
