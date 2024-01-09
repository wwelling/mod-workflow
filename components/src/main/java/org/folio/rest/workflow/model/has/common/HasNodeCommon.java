package org.folio.rest.workflow.model.has.common;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.Node Node}.
 */
public interface HasNodeCommon {

  public String getDeserializeAs();
  public String getIdentifier();

  public void setDeserializeAs(String deserializeAs);
}
