package org.folio.rest.workflow.exception;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventPublishExceptionTest {

  @Test
  void eventPublishExceptionWorksTest() {
    EventPublishException exception = Assertions.assertThrows(EventPublishException.class, () -> {
      throw new EventPublishException(VALUE, new RuntimeException("Another Exception"));
    });

    assertNotNull(exception);
  }

}
