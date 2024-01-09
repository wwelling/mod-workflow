package org.folio.rest.workflow.model.has.common;

import java.util.Set;
import org.folio.rest.workflow.model.EmbeddedRequest;
import org.folio.rest.workflow.model.EmbeddedVariable;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.RequestTask RequestTask}.
 */
public interface HasRequestTaskCommon {

  public Set<EmbeddedVariable> getHeaderOutputVariables();
  public EmbeddedRequest getRequest();

  public void setHeaderOutputVariables(Set<EmbeddedVariable> headerOutputVariables);
  public void setRequest(EmbeddedRequest request);
}
