package org.folio.rest.workflow.components;

import java.util.List;

import org.folio.rest.workflow.model.Node;

public interface Branch {

  public List<Node> getNodes();

  public void setNodes(List<Node> nodes);

}
