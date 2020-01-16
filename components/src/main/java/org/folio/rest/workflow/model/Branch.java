package org.folio.rest.workflow.model;

import java.util.List;

public interface Branch {

  public List<Node> getNodes();

  public void setNodes(List<Node> nodes);

}
