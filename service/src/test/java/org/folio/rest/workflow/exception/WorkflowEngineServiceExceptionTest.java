package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowEngineServiceExceptionTest {

  @Test
  void workflowEngineServiceExceptionWorksTest() {
    WorkflowEngineServiceException exception = Assertions.assertThrows(WorkflowEngineServiceException.class, () -> {
      throw new WorkflowEngineServiceException(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

  @Test
  void workflowEngineServiceExceptionWorksWithChildExceptionTest() {
    WorkflowEngineServiceException exception = Assertions.assertThrows(WorkflowEngineServiceException.class, () -> {
      throw new WorkflowEngineServiceException(UUID, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
