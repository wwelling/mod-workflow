package org.folio.rest.workflow.model.repo;

import org.folio.rest.workflow.model.Node;
import org.folio.spring.cql.JpaCqlRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepo extends JpaCqlRepository<Node, String> {

}
