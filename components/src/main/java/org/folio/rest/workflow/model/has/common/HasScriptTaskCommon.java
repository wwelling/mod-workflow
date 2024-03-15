package org.folio.rest.workflow.model.has.common;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.ScriptTask ScriptTask}.
 */
public interface HasScriptTaskCommon {

  public String getResultVariable();
  public String getScriptFormat();

  public void setResultVariable(String resultVariable);
  public void setScriptFormat(String scriptFormat);

  public boolean hasResultVariable();
}
