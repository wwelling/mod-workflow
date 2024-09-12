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
class AbstractProcessTest {

  @Mock
  private Node node;

  private List<Node> nodes;

  private AbstractProcess abstractProcess;

  @BeforeEach
  void beforeEach() {
    abstractProcess = new Impl();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(abstractProcess, "id", VALUE);

    assertEquals(VALUE, abstractProcess.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(abstractProcess, "id", null);

    abstractProcess.setId(VALUE);
    assertEquals(VALUE, getField(abstractProcess, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(abstractProcess, "name", VALUE);

    assertEquals(VALUE, abstractProcess.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(abstractProcess, "name", null);

    abstractProcess.setName(VALUE);
    assertEquals(VALUE, getField(abstractProcess, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(abstractProcess, "description", VALUE);

    assertEquals(VALUE, abstractProcess.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(abstractProcess, "description", null);

    abstractProcess.setDescription(VALUE);
    assertEquals(VALUE, getField(abstractProcess, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(abstractProcess, "deserializeAs", VALUE);

    assertEquals(VALUE, abstractProcess.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(abstractProcess, "deserializeAs", null);

    abstractProcess.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(abstractProcess, "deserializeAs"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(abstractProcess, "asyncBefore", true);

    assertEquals(true, abstractProcess.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(abstractProcess, "asyncBefore", false);

    abstractProcess.setAsyncBefore(true);
    assertEquals(true, getField(abstractProcess, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(abstractProcess, "asyncAfter", true);

    assertEquals(true, abstractProcess.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(abstractProcess, "asyncAfter", false);

    abstractProcess.setAsyncAfter(true);
    assertEquals(true, getField(abstractProcess, "asyncAfter"));
  }

  @Test
  void getNodesWorksTest() {
    setField(abstractProcess, "nodes", nodes);

    assertEquals(nodes, abstractProcess.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(abstractProcess, "nodes", null);

    abstractProcess.setNodes(nodes);
    assertEquals(nodes, getField(abstractProcess, "nodes"));
  }

  private static class Impl extends AbstractProcess { }

}
