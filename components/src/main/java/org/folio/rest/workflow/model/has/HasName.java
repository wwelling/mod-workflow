package org.folio.rest.workflow.model.has;

/**
 * This interface provides the Name methods to be defined.
 *
 * This is intended to be used by just about every model.
 * This has its own dedicated interface for cases where the UI may only need a list of Names returned.
 * This may often times be combined with {@link org.folio.rest.workflow.model.has.HasId HasId} to create a standard select list.
 */
public interface HasName {

  public String getName();

  public void setName(String name);
}
