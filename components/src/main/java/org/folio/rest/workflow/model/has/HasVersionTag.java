package org.folio.rest.workflow.model.has;

/**
 * This interface provides the Camunda-specific Version Tag methods.
 *
 * This should be replaced with HasVersioning once Camunda-specific code is refactored out.
 */
public interface HasVersionTag {

  public String getVersionTag();

  public void setVersionTag(String versionTag);
}
