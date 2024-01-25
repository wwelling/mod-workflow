package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.DirectoryAction;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.DirectoryTask DirectoryTask}.
 */
public interface HasDirectoryTaskCommon {

  public DirectoryAction getAction();
  public String getWorkflow();

  public void setAction(DirectoryAction action);
  public void setWorkflow(String workflow);
}
