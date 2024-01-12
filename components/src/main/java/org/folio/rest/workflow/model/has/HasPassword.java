package org.folio.rest.workflow.model.has;

/**
 * This interface provides the Password methods.
 *
 * The password usually should not be transmitted.
 * This is broken out into a separate interface so that it may be manually ignored.
 * This should not be part of any "common" interface but should instead be explicitly added for security reasons.
 */
public interface HasPassword {

  public String getPassword();

  public void setPassword(String password);
}
