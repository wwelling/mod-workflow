package org.folio.rest.workflow.model.has;

/**
 * This interface provides the ID methods to be defined.
 *
 * This is intended to be used by just about every model.
 * This has its own dedicated interface for cases where the UI may only need a list of IDs returned.
 * This may often times be combined with {@link org.folio.rest.workflow.model.has.HasName HasName} to create a standard select list.
 */
public interface HasId {

  public String getId();

  public void setId(String id);
}
