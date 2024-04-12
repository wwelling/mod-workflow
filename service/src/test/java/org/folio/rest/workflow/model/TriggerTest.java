package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.folio.rest.workflow.enums.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriggerTest {

  private Trigger trigger;

  @BeforeEach
  void beforeEach() {
    trigger = new Trigger();
  }

  @Test
  void getIdWorksTest() {
    setField(trigger, "id", VALUE);

    assertEquals(VALUE, trigger.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(trigger, "id", null);

    trigger.setId(VALUE);
    assertEquals(VALUE, getField(trigger, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(trigger, "name", VALUE);

    assertEquals(VALUE, trigger.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(trigger, "name", null);

    trigger.setName(VALUE);
    assertEquals(VALUE, getField(trigger, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(trigger, "description", VALUE);

    assertEquals(VALUE, trigger.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(trigger, "description", null);

    trigger.setDescription(VALUE);
    assertEquals(VALUE, getField(trigger, "description"));
  }

  @Test
  void getPathPatternWorksTest() {
    setField(trigger, "pathPattern", VALUE);

    assertEquals(VALUE, trigger.getPathPattern());
  }

  @Test
  void setPathPatternWorksTest() {
    setField(trigger, "pathPattern", null);

    trigger.setPathPattern(VALUE);
    assertEquals(VALUE, getField(trigger, "pathPattern"));
  }

  @Test
  void getMethodWorksTest() {
    setField(trigger, "method", HttpMethod.DELETE);

    assertEquals(HttpMethod.DELETE, trigger.getMethod());
  }

  @Test
  void setMethodWorksTest() {
    setField(trigger, "method", null);

    trigger.setMethod(HttpMethod.DELETE);
    assertEquals(HttpMethod.DELETE, getField(trigger, "method"));
  }

}
