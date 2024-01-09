package org.folio.rest.workflow.model.has;

/**
 * This interface provides the User Name methods.
 *
 * This is broken-out because of the current situation between HasService and HasUrl.
 * Once HasUrl is fully replaced with HasService, then this should be moved into HasService and this HasUsername can be fully removed.
 */
public interface HasUsername {

  public String getUsername();

  public void setUsername(String username);
}
