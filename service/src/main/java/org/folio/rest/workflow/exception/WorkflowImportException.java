package org.folio.rest.workflow.exception;

public class WorkflowImportException extends Exception {

  private static final long serialVersionUID = 1481933564332490082L;

  public WorkflowImportException(String message) {
    super(message);
  }

  public WorkflowImportException(String message, Exception e) {
    super(message, e);
  }

}
