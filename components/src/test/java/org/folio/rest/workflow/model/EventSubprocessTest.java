package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventSubprocessTest {

  @Mock
  private Node node;

  private List<Node> nodes;

  private EventSubprocess eventSubprocess;

  @BeforeEach
  void beforeEach() {
    eventSubprocess = new EventSubprocess();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(eventSubprocess, "id", VALUE);

    assertEquals(VALUE, eventSubprocess.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(eventSubprocess, "id", null);

    eventSubprocess.setId(VALUE);
    assertEquals(VALUE, getField(eventSubprocess, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(eventSubprocess, "name", VALUE);

    assertEquals(VALUE, eventSubprocess.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(eventSubprocess, "name", null);

    eventSubprocess.setName(VALUE);
    assertEquals(VALUE, getField(eventSubprocess, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(eventSubprocess, "description", VALUE);

    assertEquals(VALUE, eventSubprocess.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(eventSubprocess, "description", null);

    eventSubprocess.setDescription(VALUE);
    assertEquals(VALUE, getField(eventSubprocess, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(eventSubprocess, "deserializeAs", VALUE);

    assertEquals(VALUE, eventSubprocess.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(eventSubprocess, "deserializeAs", null);

    eventSubprocess.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(eventSubprocess, "deserializeAs"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(eventSubprocess, "asyncBefore", true);

    assertEquals(true, eventSubprocess.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(eventSubprocess, "asyncBefore", false);

    eventSubprocess.setAsyncBefore(true);
    assertEquals(true, getField(eventSubprocess, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(eventSubprocess, "asyncAfter", true);

    assertEquals(true, eventSubprocess.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(eventSubprocess, "asyncAfter", false);

    eventSubprocess.setAsyncAfter(true);
    assertEquals(true, getField(eventSubprocess, "asyncAfter"));
  }

  @Test
  void getNodesWorksTest() {
    setField(eventSubprocess, "nodes", nodes);

    assertEquals(nodes, eventSubprocess.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(eventSubprocess, "nodes", null);

    eventSubprocess.setNodes(nodes);
    assertEquals(nodes, getField(eventSubprocess, "nodes"));
  }

}
