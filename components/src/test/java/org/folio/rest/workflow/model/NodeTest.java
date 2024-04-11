package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeTest {

  private Node node;

  @BeforeEach
  void beforeEach() {
    node = new Impl();
  }

  @Test
  void getIdWorksTest() {
    setField(node, "id", VALUE);

    assertEquals(VALUE, node.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(node, "id", null);

    node.setId(VALUE);
    assertEquals(VALUE, getField(node, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(node, "name", VALUE);

    assertEquals(VALUE, node.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(node, "name", null);

    node.setName(VALUE);
    assertEquals(VALUE, getField(node, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(node, "description", VALUE);

    assertEquals(VALUE, node.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(node, "description", null);

    node.setDescription(VALUE);
    assertEquals(VALUE, getField(node, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(node, "deserializeAs", VALUE);

    assertEquals(VALUE, node.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(node, "deserializeAs", null);

    node.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(node, "deserializeAs"));
  }

  private static class Impl extends Node { };

}
