package org.folio.rest.workflow.model.has;

import java.util.List;
import org.folio.rest.workflow.model.Node;

/**
 * This interface provides methods associated with Nodes.
 */
public interface HasNodes {

  public List<Node> getNodes();

  public void setNodes(List<Node> nodes);
}
