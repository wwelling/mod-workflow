package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowNotFoundExceptionTest {

  @Test
  void workflowNotFoundExceptionWorksTest() {
    WorkflowNotFoundException exception = Assertions.assertThrows(WorkflowNotFoundException.class, () -> {
      throw new WorkflowNotFoundException(VALUE);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(VALUE));
  }

}
