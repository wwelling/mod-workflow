package org.folio.rest.workflow.exception;

public class WorkflowImportAlreadyImported extends WorkflowImportException {

  private static final long serialVersionUID = -703730144353588622L;

  private static final String MESSAGE = "The workflow cannot be imported because workflow '%s' is already imported.";

  public WorkflowImportAlreadyImported(String id) {
    super(String.format(MESSAGE, id));
  }

  public WorkflowImportAlreadyImported(String id, Exception e) {
    super(String.format(MESSAGE, id), e);
  }

}
