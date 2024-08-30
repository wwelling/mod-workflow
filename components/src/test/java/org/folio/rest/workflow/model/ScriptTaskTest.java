package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ScriptTaskTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedVariable> inputVariables;

  private ScriptTask scriptTask;

  @BeforeEach
  void beforeEach() {
    scriptTask = new ScriptTask();
  }

  @Test
  void getIdWorksTest() {
    setField(scriptTask, "id", VALUE);

    assertEquals(VALUE, scriptTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(scriptTask, "id", null);

    scriptTask.setId(VALUE);
    assertEquals(VALUE, getField(scriptTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(scriptTask, "name", VALUE);

    assertEquals(VALUE, scriptTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(scriptTask, "name", null);

    scriptTask.setName(VALUE);
    assertEquals(VALUE, getField(scriptTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(scriptTask, "description", VALUE);

    assertEquals(VALUE, scriptTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(scriptTask, "description", null);

    scriptTask.setDescription(VALUE);
    assertEquals(VALUE, getField(scriptTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(scriptTask, "deserializeAs", VALUE);

    assertEquals(VALUE, scriptTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(scriptTask, "deserializeAs", null);

    scriptTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(scriptTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(scriptTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, scriptTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(scriptTask, "inputVariables", null);

    scriptTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(scriptTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(scriptTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, scriptTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(scriptTask, "outputVariable", null);

    scriptTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(scriptTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(scriptTask, "asyncBefore", true);

    assertEquals(true, scriptTask.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(scriptTask, "asyncBefore", false);

    scriptTask.setAsyncBefore(true);
    assertEquals(true, getField(scriptTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(scriptTask, "asyncAfter", true);

    assertEquals(true, scriptTask.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(scriptTask, "asyncAfter", false);

    scriptTask.setAsyncAfter(true);
    assertEquals(true, getField(scriptTask, "asyncAfter"));
  }

  @Test
  void getScriptFormatWorksTest() {
    setField(scriptTask, "scriptFormat", VALUE);

    assertEquals(VALUE, scriptTask.getScriptFormat());
  }

  @Test
  void setScriptFormatWorksTest() {
    setField(scriptTask, "scriptFormat", null);

    scriptTask.setScriptFormat(VALUE);
    assertEquals(VALUE, getField(scriptTask, "scriptFormat"));
  }

  @Test
  void getCodeWorksTest() {
    setField(scriptTask, "code", VALUE);

    assertEquals(VALUE, scriptTask.getCode());
  }

  @Test
  void setCodeWorksTest() {
    setField(scriptTask, "code", null);

    scriptTask.setCode(VALUE);
    assertEquals(VALUE, getField(scriptTask, "code"));
  }

  @Test
  void getResultVariableWorksTest() {
    setField(scriptTask, "resultVariable", VALUE);

    assertEquals(VALUE, scriptTask.getResultVariable());
  }

  @Test
  void setResultVariableWorksTest() {
    setField(scriptTask, "resultVariable", null);

    scriptTask.setResultVariable(VALUE);
    assertEquals(VALUE, getField(scriptTask, "resultVariable"));
  }

}
