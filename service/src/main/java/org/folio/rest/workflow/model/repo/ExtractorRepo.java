package org.folio.rest.workflow.model.repo;

import org.folio.rest.workflow.model.Extractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExtractorRepo extends JpaRepository<Extractor, String> {

}
