package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputAttributeListConverterTest {

  private InputAttributeListConverter inputAttributeListConverter;

  @BeforeEach
  void beforeEach() {
    inputAttributeListConverter = new InputAttributeListConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(inputAttributeListConverter.getTypeReference());
  }

}
