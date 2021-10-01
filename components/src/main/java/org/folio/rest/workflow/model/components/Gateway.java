package org.folio.rest.workflow.model.components;

import org.folio.rest.workflow.model.Direction;

public interface Gateway extends Branch {

  public Direction getDirection();

  public void setDirection(Direction direction);

}
