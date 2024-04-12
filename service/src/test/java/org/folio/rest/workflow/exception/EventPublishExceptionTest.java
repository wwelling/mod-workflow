package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventPublishExceptionTest {

  @Test
  void eventPublishExceptionWorksTest() throws IOException {
    EventPublishException exception = Assertions.assertThrows(EventPublishException.class, () -> {
      throw new EventPublishException(VALUE, new RuntimeException("Another Exception"));
    });

    assertNotNull(exception);
  }

}
