package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringListConverterTest {

  private StringListConverter stringListConverter;

  @BeforeEach
  void beforeEach() {
    stringListConverter = new StringListConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(stringListConverter.getTypeReference());
  }

}
