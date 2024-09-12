package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DatabaseDisconnectTaskTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedVariable> inputVariables;

  private DatabaseDisconnectTask databaseDisconnectTask;

  @BeforeEach
  void beforeEach() {
    databaseDisconnectTask = new DatabaseDisconnectTask();
    inputVariables = new HashSet<>();
    inputVariables.add(embeddedVariable);
  }

  @Test
  void getIdWorksTest() {
    setField(databaseDisconnectTask, "id", VALUE);

    assertEquals(VALUE, databaseDisconnectTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(databaseDisconnectTask, "id", null);

    databaseDisconnectTask.setId(VALUE);
    assertEquals(VALUE, getField(databaseDisconnectTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(databaseDisconnectTask, "name", VALUE);

    assertEquals(VALUE, databaseDisconnectTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(databaseDisconnectTask, "name", null);

    databaseDisconnectTask.setName(VALUE);
    assertEquals(VALUE, getField(databaseDisconnectTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(databaseDisconnectTask, "description", VALUE);

    assertEquals(VALUE, databaseDisconnectTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(databaseDisconnectTask, "description", null);

    databaseDisconnectTask.setDescription(VALUE);
    assertEquals(VALUE, getField(databaseDisconnectTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(databaseDisconnectTask, "deserializeAs", VALUE);

    assertEquals(VALUE, databaseDisconnectTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(databaseDisconnectTask, "deserializeAs", null);

    databaseDisconnectTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(databaseDisconnectTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(databaseDisconnectTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, databaseDisconnectTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(databaseDisconnectTask, "inputVariables", null);

    databaseDisconnectTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(databaseDisconnectTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(databaseDisconnectTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, databaseDisconnectTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(databaseDisconnectTask, "outputVariable", null);

    databaseDisconnectTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(databaseDisconnectTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(databaseDisconnectTask, "asyncBefore", true);

    assertEquals(true, databaseDisconnectTask.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(databaseDisconnectTask, "asyncBefore", false);

    databaseDisconnectTask.setAsyncBefore(true);
    assertEquals(true, getField(databaseDisconnectTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(databaseDisconnectTask, "asyncAfter", true);

    assertEquals(true, databaseDisconnectTask.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(databaseDisconnectTask, "asyncAfter", false);

    databaseDisconnectTask.setAsyncAfter(true);
    assertEquals(true, getField(databaseDisconnectTask, "asyncAfter"));
  }

  @Test
  void getDesignationWorksTest() {
    setField(databaseDisconnectTask, "designation", VALUE);

    assertEquals(VALUE, databaseDisconnectTask.getDesignation());
  }

  @Test
  void setDesignationWorksTest() {
    setField(databaseDisconnectTask, "designation", null);

    databaseDisconnectTask.setDesignation(VALUE);
    assertEquals(VALUE, getField(databaseDisconnectTask, "designation"));
  }

}
