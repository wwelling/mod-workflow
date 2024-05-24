package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowImportRequiredFileMissingTest {

  @Test
  void workflowImportRequiredFileMissingWorksTest() {
    WorkflowImportRequiredFileMissing exception = Assertions.assertThrows(WorkflowImportRequiredFileMissing.class, () -> {
      throw new WorkflowImportRequiredFileMissing(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

  @Test
  void workflowImportRequiredFileMissingWorksWithChildExceptionTest() {
    WorkflowImportRequiredFileMissing exception = Assertions.assertThrows(WorkflowImportRequiredFileMissing.class, () -> {
      throw new WorkflowImportRequiredFileMissing(UUID, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
