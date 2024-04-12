package org.folio.rest.workflow.dto;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MappingTest {

  private Mapping mapping;

  @BeforeEach
  void beforeEach() {
    mapping = new Impl();
  }

  @Test
  void getToPropertyWorksTest() {
    setField(mapping, "toProperty", VALUE);

    assertEquals(VALUE, mapping.getToProperty());
  }

  @Test
  void setToPropertyWorksTest() {
    setField(mapping, "toProperty", null);

    mapping.setToProperty(VALUE);
    assertEquals(VALUE, getField(mapping, "toProperty"));
  }

  @Test
  void getFromPropertyWorksTest() {
    setField(mapping, "fromProperty", VALUE);

    assertEquals(VALUE, mapping.getFromProperty());
  }

  @Test
  void setFromPropertyWorksTest() {
    setField(mapping, "fromProperty", null);

    mapping.setFromProperty(VALUE);
    assertEquals(VALUE, getField(mapping, "fromProperty"));
  }

  @Test
  void getMultipleWorksTest() {
    setField(mapping, "multiple", true);

    assertEquals(true, mapping.isMultiple());
  }

  @Test
  void setMultipleWorksTest() {
    setField(mapping, "multiple", false);

    mapping.setMultiple(true);
    assertEquals(true, getField(mapping, "multiple"));
  }

  private static class Impl extends Mapping { };

}
