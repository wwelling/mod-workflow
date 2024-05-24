package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowAlreadyActiveExceptionTest {

  @Test
  void workflowAlreadyActiveExceptionWorksTest() {
    WorkflowAlreadyActiveException exception = Assertions.assertThrows(WorkflowAlreadyActiveException.class, () -> {
      throw new WorkflowAlreadyActiveException(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
