package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.SftpOp;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.FtpTask FtpTask}.
 */
public interface HasFtpTaskCommon {

  public String getDestinationPath();
  public SftpOp getOp();
  public String getOriginPath();

  public void setDestinationPath(String destinationPath);
  public void setOp(SftpOp op);
  public void setOriginPath(String originPath);
}
