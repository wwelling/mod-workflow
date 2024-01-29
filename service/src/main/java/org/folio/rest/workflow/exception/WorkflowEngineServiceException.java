package org.folio.rest.workflow.exception;

public class WorkflowEngineServiceException extends Exception {

  private static final long serialVersionUID = -2815351244979297723L;

  public WorkflowEngineServiceException(String message) {
    super(message);
  }

  public WorkflowEngineServiceException(String message, Exception e) {
    super(message, e);
  }

  public WorkflowEngineServiceException(int code) {
    super(Integer.toString(code));
  }

  public WorkflowEngineServiceException(int code, Exception e) {
    super(Integer.toString(code), e);
  }

}
