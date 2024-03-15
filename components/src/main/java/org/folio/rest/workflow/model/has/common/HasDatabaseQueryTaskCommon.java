package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.DatabaseResultType;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.DatabaseQueryTask DatabaseQueryTask}.
 */
public interface HasDatabaseQueryTaskCommon {

  public Boolean getIncludeHeader();
  public String getOutputPath();
  public String getQuery();
  public DatabaseResultType getResultType();

  public void setIncludeHeader(Boolean includeHeader);
  public void setOutputPath(String outputPath);
  public void setQuery(String query);
  public void setResultType(DatabaseResultType resultType);
}
