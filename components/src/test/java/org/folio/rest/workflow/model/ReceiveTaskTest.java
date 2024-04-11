package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReceiveTaskTest {

  private ReceiveTask receiveTask;

  @BeforeEach
  void beforeEach() {
    receiveTask = new ReceiveTask();
  }

  @Test
  void getIdWorksTest() {
    setField(receiveTask, "id", VALUE);

    assertEquals(VALUE, receiveTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(receiveTask, "id", null);

    receiveTask.setId(VALUE);
    assertEquals(VALUE, getField(receiveTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(receiveTask, "name", VALUE);

    assertEquals(VALUE, receiveTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(receiveTask, "name", null);

    receiveTask.setName(VALUE);
    assertEquals(VALUE, getField(receiveTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(receiveTask, "description", VALUE);

    assertEquals(VALUE, receiveTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(receiveTask, "description", null);

    receiveTask.setDescription(VALUE);
    assertEquals(VALUE, getField(receiveTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(receiveTask, "deserializeAs", VALUE);

    assertEquals(VALUE, receiveTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(receiveTask, "deserializeAs", null);

    receiveTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(receiveTask, "deserializeAs"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(receiveTask, "asyncBefore", true);

    assertEquals(true, receiveTask.isAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(receiveTask, "asyncBefore", false);

    receiveTask.setAsyncBefore(true);
    assertEquals(true, getField(receiveTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(receiveTask, "asyncAfter", true);

    assertEquals(true, receiveTask.isAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(receiveTask, "asyncAfter", false);

    receiveTask.setAsyncAfter(true);
    assertEquals(true, getField(receiveTask, "asyncAfter"));
  }

  @Test
  void getMessageWorksTest() {
    setField(receiveTask, "message", VALUE);

    assertEquals(VALUE, receiveTask.getMessage());
  }

  @Test
  void setMessageWorksTest() {
    setField(receiveTask, "message", null);

    receiveTask.setMessage(VALUE);
    assertEquals(VALUE, getField(receiveTask, "message"));
  }

}
