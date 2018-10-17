package org.folio.rest.model.repo;

import org.folio.rest.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkflowRepo extends JpaRepository<Workflow, String> {

}
