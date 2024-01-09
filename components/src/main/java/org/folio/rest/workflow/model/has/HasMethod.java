package org.folio.rest.workflow.model.has;

import org.springframework.http.HttpMethod;

/**
 * This interface provides the "method" methods.
 */
public interface HasMethod {

  public HttpMethod getMethod();

  public void setMethod(HttpMethod method);
}
