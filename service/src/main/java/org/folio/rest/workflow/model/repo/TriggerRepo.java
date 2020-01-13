package org.folio.rest.workflow.model.repo;

import java.util.List;

import org.folio.rest.workflow.model.Trigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TriggerRepo extends JpaRepository<Trigger, String> {

  public List<Trigger> findByDeserializeAs(String deserializeAs);

}
