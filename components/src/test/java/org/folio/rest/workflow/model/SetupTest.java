package org.folio.rest.workflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetupTest {

  private Setup setup;

  @BeforeEach
  void beforeEach() {
    setup = new Setup();
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(setup, "asyncBefore", true);

    assertEquals(true, setup.isAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(setup, "asyncBefore", false);

    setup.setAsyncBefore(true);
    assertEquals(true, getField(setup, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(setup, "asyncAfter", true);

    assertEquals(true, setup.isAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(setup, "asyncAfter", false);

    setup.setAsyncAfter(true);
    assertEquals(true, getField(setup, "asyncAfter"));
  }

}
