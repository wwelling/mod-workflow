package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.folio.rest.workflow.enums.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActionTest {

  private Action action;

  @BeforeEach
  void beforeEach() {
    action = new Action();
  }

  @Test
  void getInterfaceNameWorksTest() {
    setField(action, "interfaceName", VALUE);

    assertEquals(VALUE, action.getInterfaceName());
  }

  @Test
  void setInterfaceNameWorksTest() {
    setField(action, "interfaceName", null);

    action.setInterfaceName(VALUE);
    assertEquals(VALUE, getField(action, "interfaceName"));
  }

  @Test
  void getPathPatternWorksTest() {
    setField(action, "pathPattern", VALUE);

    assertEquals(VALUE, action.getPathPattern());
  }

  @Test
  void setPathPatternWorksTest() {
    setField(action, "pathPattern", null);

    action.setPathPattern(VALUE);
    assertEquals(VALUE, getField(action, "pathPattern"));
  }

  @Test
  void getMethodWorksTest() {
    setField(action, "method", HttpMethod.DELETE);

    assertEquals(HttpMethod.DELETE, action.getMethod());
  }

  @Test
  void setMethodWorksTest() {
    setField(action, "method", null);

    action.setMethod(HttpMethod.DELETE);
    assertEquals(HttpMethod.DELETE, getField(action, "method"));
  }

}
