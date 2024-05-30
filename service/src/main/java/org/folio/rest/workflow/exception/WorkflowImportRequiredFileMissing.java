package org.folio.rest.workflow.exception;

public class WorkflowImportRequiredFileMissing extends WorkflowImportException {

  private static final long serialVersionUID = -8957979238753187195L;

  private static final String MESSAGE = "The workflow cannot be imported because the required file '%s' is missing.";

  public WorkflowImportRequiredFileMissing(String name) {
    super(String.format(MESSAGE, name));
  }

  public WorkflowImportRequiredFileMissing(String name, Exception e) {
    super(String.format(MESSAGE, name), e);
  }

}
