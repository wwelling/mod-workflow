package org.folio.rest.model.repo;

import org.folio.rest.model.Trigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TriggerRepo extends JpaRepository<Trigger, String> {

}
