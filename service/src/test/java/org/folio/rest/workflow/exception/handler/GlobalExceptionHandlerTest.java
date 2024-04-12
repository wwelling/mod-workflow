package org.folio.rest.workflow.exception.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.folio.spring.web.model.response.ResponseErrors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  private static final RuntimeException runtimeException = new RuntimeException("A runtime failure.");

  @Mock
  private TransactionSystemException transactionSystemException;

  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  void beforeEach() {
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  void handleConstraintViolationWorksTest() throws Exception {
    when(transactionSystemException.getRootCause()).thenReturn(runtimeException);
    ResponseEntity<ResponseErrors> response = globalExceptionHandler.handleConstraintViolation(transactionSystemException);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

}
