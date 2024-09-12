package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmbeddedVariableConverterTest {

  private EmbeddedVariableConverter embeddedVariableConverter;

  @BeforeEach
  void beforeEach() {
    embeddedVariableConverter = new EmbeddedVariableConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(embeddedVariableConverter.getTypeReference());
  }

}
