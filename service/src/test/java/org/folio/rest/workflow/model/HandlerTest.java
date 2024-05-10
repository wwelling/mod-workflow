package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.STRING_LIST;
import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandlerTest {

  private Handler handler;

  @BeforeEach
  void beforeEach() {
    handler = new Handler();
  }

  @Test
  void getMethodsWorksTest() {
    setField(handler, "methods", STRING_LIST);

    assertEquals(STRING_LIST, handler.getMethods());
  }

  @Test
  void setMethodsWorksTest() {
    setField(handler, "methods", null);

    handler.setMethods(STRING_LIST);
    assertEquals(STRING_LIST, getField(handler, "methods"));
  }

  @Test
  void getPathPatternWorksTest() {
    setField(handler, "pathPattern", VALUE);

    assertEquals(VALUE, handler.getPathPattern());
  }

  @Test
  void setPathPatternWorksTest() {
    setField(handler, "pathPattern", null);

    handler.setPathPattern(VALUE);
    assertEquals(VALUE, getField(handler, "pathPattern"));
  }

  @Test
  void getPermissionsRequiredWorksTest() {
    setField(handler, "permissionsRequired", STRING_LIST);

    assertEquals(STRING_LIST, handler.getPermissionsRequired());
  }

  @Test
  void setPermissionsRequiredWorksTest() {
    setField(handler, "permissionsRequired", null);

    handler.setPermissionsRequired(STRING_LIST);
    assertEquals(STRING_LIST, getField(handler, "permissionsRequired"));
  }

  @Test
  void getPermissionsDesiredWorksTest() {
    setField(handler, "permissionsDesired", STRING_LIST);

    assertEquals(STRING_LIST, handler.getPermissionsDesired());
  }

  @Test
  void setPermissionsDesiredWorksTest() {
    setField(handler, "permissionsDesired", null);

    handler.setPermissionsDesired(STRING_LIST);
    assertEquals(STRING_LIST, getField(handler, "permissionsDesired"));
  }

}
