package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import org.folio.rest.workflow.enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoveToLastGatewayTest {

  @Mock
  private Node node;

  private List<Node> nodes;

  private MoveToLastGateway moveToLastGateway;

  @BeforeEach
  void beforeEach() {
    moveToLastGateway = new MoveToLastGateway();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(moveToLastGateway, "id", VALUE);

    assertEquals(VALUE, moveToLastGateway.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(moveToLastGateway, "id", null);

    moveToLastGateway.setId(VALUE);
    assertEquals(VALUE, getField(moveToLastGateway, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(moveToLastGateway, "name", VALUE);

    assertEquals(VALUE, moveToLastGateway.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(moveToLastGateway, "name", null);

    moveToLastGateway.setName(VALUE);
    assertEquals(VALUE, getField(moveToLastGateway, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(moveToLastGateway, "description", VALUE);

    assertEquals(VALUE, moveToLastGateway.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(moveToLastGateway, "description", null);

    moveToLastGateway.setDescription(VALUE);
    assertEquals(VALUE, getField(moveToLastGateway, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(moveToLastGateway, "deserializeAs", VALUE);

    assertEquals(VALUE, moveToLastGateway.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(moveToLastGateway, "deserializeAs", null);

    moveToLastGateway.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(moveToLastGateway, "deserializeAs"));
  }

  @Test
  void getDirectionWorksTest() {
    setField(moveToLastGateway, "direction", Direction.CONVERGING);

    assertEquals(Direction.CONVERGING, moveToLastGateway.getDirection());
  }

  @Test
  void setDirectionWorksTest() {
    setField(moveToLastGateway, "direction", null);

    moveToLastGateway.setDirection(Direction.CONVERGING);
    assertEquals(Direction.CONVERGING, getField(moveToLastGateway, "direction"));
  }

  @Test
  void getNodesWorksTest() {
    setField(moveToLastGateway, "nodes", nodes);

    assertEquals(nodes, moveToLastGateway.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(moveToLastGateway, "nodes", null);

    moveToLastGateway.setNodes(nodes);
    assertEquals(nodes, getField(moveToLastGateway, "nodes"));
  }

}
