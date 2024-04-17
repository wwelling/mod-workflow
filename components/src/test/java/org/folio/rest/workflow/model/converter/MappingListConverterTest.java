package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MappingListConverterTest {

  private MappingListConverter mappingListConverter;

  @BeforeEach
  void beforeEach() {
    mappingListConverter = new MappingListConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(mappingListConverter.getTypeReference());
  }

}
