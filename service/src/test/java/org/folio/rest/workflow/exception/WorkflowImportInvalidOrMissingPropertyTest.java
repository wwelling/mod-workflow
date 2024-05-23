package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.URL;
import static org.folio.spring.test.mock.MockMvcConstant.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowImportInvalidOrMissingPropertyTest {

  @Test
  void workflowImportInvalidOrMissingPropertyWorksTest() throws IOException {
    WorkflowImportInvalidOrMissingProperty exception = Assertions.assertThrows(WorkflowImportInvalidOrMissingProperty.class, () -> {
      throw new WorkflowImportInvalidOrMissingProperty(UUID, URL);
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
    assertTrue(exception.getMessage().contains(URL));
  }

  @Test
  void workflowImportInvalidOrMissingPropertyWorksWithChildExceptionTest() throws IOException {
    WorkflowImportInvalidOrMissingProperty exception = Assertions.assertThrows(WorkflowImportInvalidOrMissingProperty.class, () -> {
      throw new WorkflowImportInvalidOrMissingProperty(UUID, URL, new RuntimeException("Additional Exception"));
    });

    assertNotNull(exception);
    assertTrue(exception.getMessage().contains(UUID));
    assertTrue(exception.getMessage().contains(URL));
  }

}
