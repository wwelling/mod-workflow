package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonNodeConverterTest {

  private JsonNodeConverter jsonNodeConverter;

  @BeforeEach
  void beforeEach() {
    jsonNodeConverter = new JsonNodeConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(jsonNodeConverter.getTypeReference());
  }

}
