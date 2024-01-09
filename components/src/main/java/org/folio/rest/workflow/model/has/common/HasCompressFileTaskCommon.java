package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.CompressFileContainer;
import org.folio.rest.workflow.enums.CompressFileFormat;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.CompressFileTask CompressFileTask}.
 */
public interface HasCompressFileTaskCommon {

  public CompressFileContainer getContainer();
  public String getDestination();
  public CompressFileFormat getFormat();
  public String getSource();

  public void setContainer(CompressFileContainer container);
  public void setDestination(String destination);
  public void setFormat(CompressFileFormat format);
  public void setSource(String source);
}
