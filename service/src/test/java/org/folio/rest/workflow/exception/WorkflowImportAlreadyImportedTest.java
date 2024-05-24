package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowImportAlreadyImportedTest {

  @Test
  void workflowImportAlreadyImportedWorksTest() {
    WorkflowImportAlreadyImported exception = Assertions.assertThrows(WorkflowImportAlreadyImported.class, () -> {
      throw new WorkflowImportAlreadyImported(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

  @Test
  void workflowImportAlreadyImportedWorksWithChildExceptionTest() throws IOException {
    WorkflowImportAlreadyImported exception = Assertions.assertThrows(WorkflowImportAlreadyImported.class, () -> {
      throw new WorkflowImportAlreadyImported(UUID, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
