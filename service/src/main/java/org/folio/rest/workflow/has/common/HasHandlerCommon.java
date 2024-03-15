package org.folio.rest.workflow.has.common;

import java.util.List;
import org.folio.rest.workflow.model.Action;

/**
 * This interface provides common methods for {@link org.folio.rest.workflow.model.Handler Handler}.
 */
public interface HasHandlerCommon {

  public List<Action> getActionByInterface(String interfaceName);
  public List<String> getMethods();
  public List<String> getPermissionsDesired();
  public List<String> getPermissionsRequired();

  public void setMethods(List<String> methods);
  public void setPermissionsDesired(List<String> permissionsDesired);
  public void setPermissionsRequired(List<String> permissionsRequired);

}
