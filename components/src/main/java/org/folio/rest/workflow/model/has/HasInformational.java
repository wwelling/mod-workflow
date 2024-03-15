package org.folio.rest.workflow.model.has;

/**
 * This interface provides core methods classified as "informational".
 *
 * These methods are used to provide additional useful information.
 * This is a list of those methods that are shared across most models.
 */
public interface HasInformational {

  public String getDescription();

  public void setDescription(String description);
}
