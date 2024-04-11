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
class ExclusiveGatewayTest {

  @Mock
  private Node node;

  private List<Node> nodes;

  private ExclusiveGateway exclusiveGateway;

  @BeforeEach
  void beforeEach() {
    exclusiveGateway = new ExclusiveGateway();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(exclusiveGateway, "id", VALUE);

    assertEquals(VALUE, exclusiveGateway.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(exclusiveGateway, "id", null);

    exclusiveGateway.setId(VALUE);
    assertEquals(VALUE, getField(exclusiveGateway, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(exclusiveGateway, "name", VALUE);

    assertEquals(VALUE, exclusiveGateway.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(exclusiveGateway, "name", null);

    exclusiveGateway.setName(VALUE);
    assertEquals(VALUE, getField(exclusiveGateway, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(exclusiveGateway, "description", VALUE);

    assertEquals(VALUE, exclusiveGateway.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(exclusiveGateway, "description", null);

    exclusiveGateway.setDescription(VALUE);
    assertEquals(VALUE, getField(exclusiveGateway, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(exclusiveGateway, "deserializeAs", VALUE);

    assertEquals(VALUE, exclusiveGateway.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(exclusiveGateway, "deserializeAs", null);

    exclusiveGateway.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(exclusiveGateway, "deserializeAs"));
  }

  @Test
  void getDirectionWorksTest() {
    setField(exclusiveGateway, "direction", Direction.CONVERGING);

    assertEquals(Direction.CONVERGING, exclusiveGateway.getDirection());
  }

  @Test
  void setDirectionWorksTest() {
    setField(exclusiveGateway, "direction", null);

    exclusiveGateway.setDirection(Direction.CONVERGING);
    assertEquals(Direction.CONVERGING, getField(exclusiveGateway, "direction"));
  }

  @Test
  void getNodesWorksTest() {
    setField(exclusiveGateway, "nodes", nodes);

    assertEquals(nodes, exclusiveGateway.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(exclusiveGateway, "nodes", null);

    exclusiveGateway.setNodes(nodes);
    assertEquals(nodes, getField(exclusiveGateway, "nodes"));
  }

}
