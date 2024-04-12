package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestConverterTest {

  private RequestConverter requestConverter;

  @BeforeEach
  void beforeEach() {
    requestConverter = new RequestConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(requestConverter.getTypeReference());
  }

}
