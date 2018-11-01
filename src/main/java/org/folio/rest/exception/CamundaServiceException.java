package org.folio.rest.exception;

public class CamundaServiceException extends Exception {

  private static final long serialVersionUID = -2941173032626051479L;

  private static String CAMUNDA_EXCEPTION_MESSAGE = "Error comunicating with mod-camunda: {}";

  public CamundaServiceException(int code) {
    super(Integer.toString(code));
  }

  public CamundaServiceException(String reason) {
    super(String.format(CAMUNDA_EXCEPTION_MESSAGE, reason));
  }

}
