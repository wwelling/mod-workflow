package org.folio.rest.workflow.model.repo;

import org.folio.rest.workflow.model.Workflow;
import org.folio.spring.cql.JpaCqlRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkflowRepo extends JpaCqlRepository<Workflow, String> {

  public <T> T getViewById(String id, Class<T> type);
}
