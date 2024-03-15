package org.folio.rest.workflow.model.has.common;

import org.folio.rest.workflow.enums.ScriptType;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.EmbeddedProcessor EmbeddedProcessor}.
 */
public interface HasEmbeddedProcessorCommon {

  public int getBuffer();
  public String getCode();
  public int getDelay();
  public String getFunctionName();
  public ScriptType getScriptType();

  public void setBuffer(int buffer);
  public void setCode(String code);
  public void setDelay(int delay);
  public void setFunctionName(String functionName);
  public void setScriptType(ScriptType scriptType);
}
