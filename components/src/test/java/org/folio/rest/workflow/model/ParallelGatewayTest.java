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
class ParallelGatewayTest {

  @Mock
  private Node node;

  private List<Node> nodes;

  private ParallelGateway parallelGateway;

  @BeforeEach
  void beforeEach() {
    parallelGateway = new ParallelGateway();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(parallelGateway, "id", VALUE);

    assertEquals(VALUE, parallelGateway.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(parallelGateway, "id", null);

    parallelGateway.setId(VALUE);
    assertEquals(VALUE, getField(parallelGateway, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(parallelGateway, "name", VALUE);

    assertEquals(VALUE, parallelGateway.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(parallelGateway, "name", null);

    parallelGateway.setName(VALUE);
    assertEquals(VALUE, getField(parallelGateway, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(parallelGateway, "description", VALUE);

    assertEquals(VALUE, parallelGateway.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(parallelGateway, "description", null);

    parallelGateway.setDescription(VALUE);
    assertEquals(VALUE, getField(parallelGateway, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(parallelGateway, "deserializeAs", VALUE);

    assertEquals(VALUE, parallelGateway.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(parallelGateway, "deserializeAs", null);

    parallelGateway.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(parallelGateway, "deserializeAs"));
  }

  @Test
  void getDirectionWorksTest() {
    setField(parallelGateway, "direction", Direction.CONVERGING);

    assertEquals(Direction.CONVERGING, parallelGateway.getDirection());
  }

  @Test
  void setDirectionWorksTest() {
    setField(parallelGateway, "direction", null);

    parallelGateway.setDirection(Direction.CONVERGING);
    assertEquals(Direction.CONVERGING, getField(parallelGateway, "direction"));
  }

  @Test
  void getNodesWorksTest() {
    setField(parallelGateway, "nodes", nodes);

    assertEquals(nodes, parallelGateway.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(parallelGateway, "nodes", null);

    parallelGateway.setNodes(nodes);
    assertEquals(nodes, getField(parallelGateway, "nodes"));
  }

}
