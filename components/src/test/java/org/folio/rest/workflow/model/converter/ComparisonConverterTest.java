package org.folio.rest.workflow.model.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComparisonConverterTest {

  private ComparisonListConverter comparisonListConverter;

  @BeforeEach
  void beforeEach() {
    comparisonListConverter = new ComparisonListConverter();
  }

  @Test
  void getTypeReferenceWorksTest() {
    assertNotNull(comparisonListConverter.getTypeReference());
  }

}
