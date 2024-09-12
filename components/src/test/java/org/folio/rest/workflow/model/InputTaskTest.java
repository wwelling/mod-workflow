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
class InputTaskTest {

  @Mock
  private EmbeddedInput input;

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedInput> inputs;

  private Set<EmbeddedVariable> inputVariables;

  private InputTask inputTask;

  @BeforeEach
  void beforeEach() {
    inputs = new HashSet<>();
    inputs.add(input);

    inputVariables = new HashSet<>();
    inputVariables.add(embeddedVariable);

    inputTask = new InputTask();
  }

  @Test
  void getIdWorksTest() {
    setField(inputTask, "id", VALUE);

    assertEquals(VALUE, inputTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(inputTask, "id", null);

    inputTask.setId(VALUE);
    assertEquals(VALUE, getField(inputTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(inputTask, "name", VALUE);

    assertEquals(VALUE, inputTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(inputTask, "name", null);

    inputTask.setName(VALUE);
    assertEquals(VALUE, getField(inputTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(inputTask, "description", VALUE);

    assertEquals(VALUE, inputTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(inputTask, "description", null);

    inputTask.setDescription(VALUE);
    assertEquals(VALUE, getField(inputTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(inputTask, "deserializeAs", VALUE);

    assertEquals(VALUE, inputTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(inputTask, "deserializeAs", null);

    inputTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(inputTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(inputTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, inputTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(inputTask, "inputVariables", null);

    inputTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(inputTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(inputTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, inputTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(inputTask, "outputVariable", null);

    inputTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(inputTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(inputTask, "asyncBefore", true);

    assertEquals(true, inputTask.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(inputTask, "asyncBefore", false);

    inputTask.setAsyncBefore(true);
    assertEquals(true, getField(inputTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(inputTask, "asyncAfter", true);

    assertEquals(true, inputTask.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(inputTask, "asyncAfter", false);

    inputTask.setAsyncAfter(true);
    assertEquals(true, getField(inputTask, "asyncAfter"));
  }

  @Test
  void getInputsWorksTest() {
    setField(inputTask, "inputs", inputs);

    assertEquals(inputs, inputTask.getInputs());
  }

  @Test
  void setInputsWorksTest() {
    setField(inputTask, "inputs", null);

    inputTask.setInputs(inputs);
    assertEquals(inputs, getField(inputTask, "inputs"));
  }

}
