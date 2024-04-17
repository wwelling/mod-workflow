package org.folio.rest.workflow.dto;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComparisonTest {

  private Comparison comparison;

  @BeforeEach
  void beforeEach() {
    comparison = new Impl();
  }

  @Test
  void getSourcePropertyWorksTest() {
    setField(comparison, "sourceProperty", VALUE);

    assertEquals(VALUE, comparison.getSourceProperty());
  }

  @Test
  void setSourcePropertyWorksTest() {
    setField(comparison, "sourceProperty", null);

    comparison.setSourceProperty(VALUE);
    assertEquals(VALUE, getField(comparison, "sourceProperty"));
  }

  @Test
  void getTargetPropertyWorksTest() {
    setField(comparison, "targetProperty", VALUE);

    assertEquals(VALUE, comparison.getTargetProperty());
  }

  @Test
  void setTargetPropertyWorksTest() {
    setField(comparison, "targetProperty", null);

    comparison.setTargetProperty(VALUE);
    assertEquals(VALUE, getField(comparison, "targetProperty"));
  }

  private static class Impl extends Comparison { };

}
