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
class AbstractTaskTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedVariable> inputVariables;

  @Mock
  private Node node;

  private List<Node> nodes;

  private AbstractTask abstractTask;

  @BeforeEach
  void beforeEach() {
    abstractTask = new Impl();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(abstractTask, "id", VALUE);

    assertEquals(VALUE, abstractTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(abstractTask, "id", null);

    abstractTask.setId(VALUE);
    assertEquals(VALUE, getField(abstractTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(abstractTask, "name", VALUE);

    assertEquals(VALUE, abstractTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(abstractTask, "name", null);

    abstractTask.setName(VALUE);
    assertEquals(VALUE, getField(abstractTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(abstractTask, "description", VALUE);

    assertEquals(VALUE, abstractTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(abstractTask, "description", null);

    abstractTask.setDescription(VALUE);
    assertEquals(VALUE, getField(abstractTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(abstractTask, "deserializeAs", VALUE);

    assertEquals(VALUE, abstractTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(abstractTask, "deserializeAs", null);

    abstractTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(abstractTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(abstractTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, abstractTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(abstractTask, "inputVariables", null);

    abstractTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(abstractTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(abstractTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, abstractTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(abstractTask, "outputVariable", null);

    abstractTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(abstractTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(abstractTask, "asyncBefore", true);

    assertEquals(true, abstractTask.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(abstractTask, "asyncBefore", false);

    abstractTask.setAsyncBefore(true);
    assertEquals(true, getField(abstractTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(abstractTask, "asyncAfter", true);

    assertEquals(true, abstractTask.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(abstractTask, "asyncAfter", false);

    abstractTask.setAsyncAfter(true);
    assertEquals(true, getField(abstractTask, "asyncAfter"));
  }

  private static class Impl extends AbstractTask { }

}
