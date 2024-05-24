package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowImportJsonFileIsDirectoryTest {

  @Test
  void workflowImportJsonFileIsDirectoryWorksTest() {
    WorkflowImportJsonFileIsDirectory exception = Assertions.assertThrows(WorkflowImportJsonFileIsDirectory.class, () -> {
      throw new WorkflowImportJsonFileIsDirectory(UUID);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

  @Test
  void workflowImportJsonFileIsDirectoryWorksWithChildExceptionTest()  {
    WorkflowImportJsonFileIsDirectory exception = Assertions.assertThrows(WorkflowImportJsonFileIsDirectory.class, () -> {
      throw new WorkflowImportJsonFileIsDirectory(UUID, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
  }

}
