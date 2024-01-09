package org.folio.rest.workflow.dto;

import org.folio.rest.workflow.model.has.HasId;
import org.folio.rest.workflow.model.has.HasInformational;
import org.folio.rest.workflow.model.has.HasMethod;
import org.folio.rest.workflow.model.has.HasName;
import org.folio.rest.workflow.model.has.HasPathPattern;

/**
 * This is a DTO design for providing the entire {@link org.folio.rest.workflow.model.Trigger Trigger} model.
 */
public interface TriggerDto extends HasId, HasInformational, HasMethod, HasName, HasPathPattern {

}
