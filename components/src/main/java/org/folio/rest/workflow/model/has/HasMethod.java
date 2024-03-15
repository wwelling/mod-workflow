package org.folio.rest.workflow.model.has;

import org.folio.rest.workflow.enums.HttpMethod;

/**
 * This interface provides the "method" methods.
 */
public interface HasMethod {

  public HttpMethod getMethod();

  public void setMethod(HttpMethod method);
}
