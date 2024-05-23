package org.folio.rest.workflow.exception;

public class WorkflowImportInvalidOrMissingProperty extends WorkflowImportException {

  private static final long serialVersionUID = -703730144353588622L;

  private static final String MESSAGE = "The workflow cannot be imported because '%s' has an invalid or a missing property '%s'.";

  public WorkflowImportInvalidOrMissingProperty(String name, String property) {
    super(String.format(MESSAGE, name, property));
  }

  public WorkflowImportInvalidOrMissingProperty(String name, String property, Exception e) {
    super(String.format(MESSAGE, name, property), e);
  }

}
