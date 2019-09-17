package org.folio.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({ TransactionSystemException.class })
  public ResponseEntity<String> handleConstraintViolation(Exception ex, WebRequest request) {
    Throwable cause = ((TransactionSystemException) ex).getRootCause();
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(cause.getMessage().toString());
  }
}
