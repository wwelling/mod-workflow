package org.folio.rest.workflow.model.repo;

import java.util.List;
import org.folio.rest.workflow.model.Trigger;
import org.folio.spring.cql.JpaCqlRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TriggerRepo extends JpaCqlRepository<Trigger, String> {

  public <T> List<T> findViewAllBy(Class<T> type);
}
