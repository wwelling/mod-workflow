package org.folio.rest.workflow.model.has.common;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import org.folio.rest.workflow.model.Setup;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.Workflow Workflow}.
 */
public interface HasWorkflowCommon {

  public Boolean getActive();
  public Integer getHistoryTimeToLive();
  public Map<String, JsonNode> getInitialContext();
  public Setup getSetup();

  public void setActive(Boolean active);
  public void setHistoryTimeToLive(Integer historyTimeToLive);
  public void setInitialContext(Map<String, JsonNode> initialContext);
  public void setSetup(Setup setup);
}
