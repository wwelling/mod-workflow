package org.folio.rest.workflow.model.has;

/**
 * This interface provides core methods classified as "versioning".
 *
 * These methods are used to provide tracking of particular version related details.
 * This is a list of those methods that are shared across most models.
 *
 * Some models have "versionTag", which is camunda-specific.
 * Such models should implement this for consistency purposes and then have these call the getVersionTag() or setVersionTag() for getVersion() and setVersion().
 * This behavior should be removed once Camunda-specific functionality is refactored out.
 */
public interface HasVersioning {

  public String getVersion();

  public void setVersion(String version);
}
