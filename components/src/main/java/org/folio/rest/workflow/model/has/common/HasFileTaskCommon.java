package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.FileOp;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.FileTask FileTask}.
 */
public interface HasFileTaskCommon {

  public String getLine();
  public FileOp getOp();
  public String getTarget();

  public void setLine(String line);
  public void setOp(FileOp op);
  public void setTarget(String target);
}
