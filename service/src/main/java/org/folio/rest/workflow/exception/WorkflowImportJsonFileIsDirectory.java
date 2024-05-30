package org.folio.rest.workflow.exception;

public class WorkflowImportJsonFileIsDirectory extends WorkflowImportException {

  private static final long serialVersionUID = -1861735040910804859L;

  private static final String MESSAGE = "The workflow cannot be imported because the JSON file '%s' is a directory.";

  public WorkflowImportJsonFileIsDirectory(String name) {
    super(String.format(MESSAGE, name));
  }

  public WorkflowImportJsonFileIsDirectory(String name, Exception e) {
    super(String.format(MESSAGE, name), e);
  }

}
