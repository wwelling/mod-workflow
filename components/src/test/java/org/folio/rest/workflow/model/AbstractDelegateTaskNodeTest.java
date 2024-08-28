package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractDelegateTaskNodeTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedVariable> inputVariables;

  @Mock
  private Node node;

  private List<Node> nodes;

  private AbstractDelegateTaskNode abstractDelegateTaskNode;

  @BeforeEach
  void beforeEach() {
    abstractDelegateTaskNode = new Impl();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(abstractDelegateTaskNode, "id", VALUE);

    assertEquals(VALUE, abstractDelegateTaskNode.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(abstractDelegateTaskNode, "id", null);

    abstractDelegateTaskNode.setId(VALUE);
    assertEquals(VALUE, getField(abstractDelegateTaskNode, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(abstractDelegateTaskNode, "name", VALUE);

    assertEquals(VALUE, abstractDelegateTaskNode.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(abstractDelegateTaskNode, "name", null);

    abstractDelegateTaskNode.setName(VALUE);
    assertEquals(VALUE, getField(abstractDelegateTaskNode, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(abstractDelegateTaskNode, "description", VALUE);

    assertEquals(VALUE, abstractDelegateTaskNode.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(abstractDelegateTaskNode, "description", null);

    abstractDelegateTaskNode.setDescription(VALUE);
    assertEquals(VALUE, getField(abstractDelegateTaskNode, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(abstractDelegateTaskNode, "deserializeAs", VALUE);

    assertEquals(VALUE, abstractDelegateTaskNode.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(abstractDelegateTaskNode, "deserializeAs", null);

    abstractDelegateTaskNode.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(abstractDelegateTaskNode, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(abstractDelegateTaskNode, "inputVariables", inputVariables);

    assertEquals(inputVariables, abstractDelegateTaskNode.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(abstractDelegateTaskNode, "inputVariables", null);

    abstractDelegateTaskNode.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(abstractDelegateTaskNode, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(abstractDelegateTaskNode, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, abstractDelegateTaskNode.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(abstractDelegateTaskNode, "outputVariable", null);

    abstractDelegateTaskNode.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(abstractDelegateTaskNode, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(abstractDelegateTaskNode, "asyncBefore", true);

    assertEquals(true, abstractDelegateTaskNode.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(abstractDelegateTaskNode, "asyncBefore", false);

    abstractDelegateTaskNode.setAsyncBefore(true);
    assertEquals(true, getField(abstractDelegateTaskNode, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(abstractDelegateTaskNode, "asyncAfter", true);

    assertEquals(true, abstractDelegateTaskNode.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(abstractDelegateTaskNode, "asyncAfter", false);

    abstractDelegateTaskNode.setAsyncAfter(true);
    assertEquals(true, getField(abstractDelegateTaskNode, "asyncAfter"));
  }

  private static class Impl extends AbstractDelegateTaskNode { };

}
