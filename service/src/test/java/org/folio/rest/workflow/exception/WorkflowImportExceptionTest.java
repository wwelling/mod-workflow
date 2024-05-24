package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowImportExceptionTest {

  @Test
  void workflowImportExceptionWorksTest() {
    WorkflowImportException exception = Assertions.assertThrows(WorkflowImportException.class, () -> {
      throw new WorkflowImportException(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

  @Test
  void workflowImportExceptionWorksWithChildExceptionTest() {
    WorkflowImportException exception = Assertions.assertThrows(WorkflowImportException.class, () -> {
      throw new WorkflowImportException(UUID, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
