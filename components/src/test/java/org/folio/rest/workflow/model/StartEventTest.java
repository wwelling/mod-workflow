package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.folio.rest.workflow.enums.StartEventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StartEventTest {

  private StartEvent startEvent;

  @BeforeEach
  void beforeEach() {
    startEvent = new Impl();
  }

  @Test
  void getIdWorksTest() {
    setField(startEvent, "id", VALUE);

    assertEquals(VALUE, startEvent.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(startEvent, "id", null);

    startEvent.setId(VALUE);
    assertEquals(VALUE, getField(startEvent, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(startEvent, "name", VALUE);

    assertEquals(VALUE, startEvent.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(startEvent, "name", null);

    startEvent.setName(VALUE);
    assertEquals(VALUE, getField(startEvent, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(startEvent, "description", VALUE);

    assertEquals(VALUE, startEvent.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(startEvent, "description", null);

    startEvent.setDescription(VALUE);
    assertEquals(VALUE, getField(startEvent, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(startEvent, "deserializeAs", VALUE);

    assertEquals(VALUE, startEvent.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(startEvent, "deserializeAs", null);

    startEvent.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(startEvent, "deserializeAs"));
  }

  @Test
  void getTypeWorksTest() {
    setField(startEvent, "type", StartEventType.MESSAGE_CORRELATION);

    assertEquals(StartEventType.MESSAGE_CORRELATION, startEvent.getType());
  }

  @Test
  void setTypeWorksTest() {
    setField(startEvent, "type", null);

    startEvent.setType(StartEventType.MESSAGE_CORRELATION);
    assertEquals(StartEventType.MESSAGE_CORRELATION, getField(startEvent, "type"));
  }

  @Test
  void getExpressionWorksTest() {
    setField(startEvent, "expression", VALUE);

    assertEquals(VALUE, startEvent.getExpression());
  }

  @Test
  void setExpressionWorksTest() {
    setField(startEvent, "expression", null);

    startEvent.setExpression(VALUE);
    assertEquals(VALUE, getField(startEvent, "expression"));
  }

  @Test
  void getInterruptingWorksTest() {
    setField(startEvent, "interrupting", true);

    assertEquals(true, startEvent.getInterrupting());
  }

  @Test
  void setInterruptingWorksTest() {
    setField(startEvent, "interrupting", false);

    startEvent.setInterrupting(true);
    assertEquals(true, getField(startEvent, "interrupting"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(startEvent, "asyncBefore", true);

    assertEquals(true, startEvent.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(startEvent, "asyncBefore", false);

    startEvent.setAsyncBefore(true);
    assertEquals(true, getField(startEvent, "asyncBefore"));
  }

  private static class Impl extends StartEvent { };

}
