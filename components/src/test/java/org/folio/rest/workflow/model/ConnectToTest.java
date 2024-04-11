package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectToTest {

  private ConnectTo connectTo;

  @BeforeEach
  void beforeEach() {
    connectTo = new ConnectTo();
  }

  @Test
  void getIdWorksTest() {
    setField(connectTo, "id", VALUE);

    assertEquals(VALUE, connectTo.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(connectTo, "id", null);

    connectTo.setId(VALUE);
    assertEquals(VALUE, getField(connectTo, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(connectTo, "name", VALUE);

    assertEquals(VALUE, connectTo.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(connectTo, "name", null);

    connectTo.setName(VALUE);
    assertEquals(VALUE, getField(connectTo, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(connectTo, "description", VALUE);

    assertEquals(VALUE, connectTo.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(connectTo, "description", null);

    connectTo.setDescription(VALUE);
    assertEquals(VALUE, getField(connectTo, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(connectTo, "deserializeAs", VALUE);

    assertEquals(VALUE, connectTo.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(connectTo, "deserializeAs", null);

    connectTo.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(connectTo, "deserializeAs"));
  }

  @Test
  void getNodeIdWorksTest() {
    setField(connectTo, "nodeId", VALUE);

    assertEquals(VALUE, connectTo.getNodeId());
  }

  @Test
  void setNodeIdWorksTest() {
    setField(connectTo, "nodeId", null);

    connectTo.setNodeId(VALUE);
    assertEquals(VALUE, getField(connectTo, "nodeId"));
  }

}
