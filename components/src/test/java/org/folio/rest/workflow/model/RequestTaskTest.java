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
class RequestTaskTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  @Mock
  private EmbeddedRequest embeddedRequest;

  private Set<EmbeddedVariable> inputVariables;

  private RequestTask requestTask;

  @BeforeEach
  void beforeEach() {
    requestTask = new RequestTask();
    inputVariables = new HashSet<>();
    inputVariables.add(embeddedVariable);
  }

  @Test
  void getIdWorksTest() {
    setField(requestTask, "id", VALUE);

    assertEquals(VALUE, requestTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(requestTask, "id", null);

    requestTask.setId(VALUE);
    assertEquals(VALUE, getField(requestTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(requestTask, "name", VALUE);

    assertEquals(VALUE, requestTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(requestTask, "name", null);

    requestTask.setName(VALUE);
    assertEquals(VALUE, getField(requestTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(requestTask, "description", VALUE);

    assertEquals(VALUE, requestTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(requestTask, "description", null);

    requestTask.setDescription(VALUE);
    assertEquals(VALUE, getField(requestTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(requestTask, "deserializeAs", VALUE);

    assertEquals(VALUE, requestTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(requestTask, "deserializeAs", null);

    requestTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(requestTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(requestTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, requestTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(requestTask, "inputVariables", null);

    requestTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(requestTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(requestTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, requestTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(requestTask, "outputVariable", null);

    requestTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(requestTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(requestTask, "asyncBefore", true);

    assertEquals(true, requestTask.getAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(requestTask, "asyncBefore", false);

    requestTask.setAsyncBefore(true);
    assertEquals(true, getField(requestTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(requestTask, "asyncAfter", true);

    assertEquals(true, requestTask.getAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(requestTask, "asyncAfter", false);

    requestTask.setAsyncAfter(true);
    assertEquals(true, getField(requestTask, "asyncAfter"));
  }

  @Test
  void getHeaderOutputVariablesWorksTest() {
    setField(requestTask, "headerOutputVariables", inputVariables);

    assertEquals(inputVariables, requestTask.getHeaderOutputVariables());
  }

  @Test
  void setHeaderOutputVariablesWorksTest() {
    setField(requestTask, "headerOutputVariables", null);

    requestTask.setHeaderOutputVariables(inputVariables);
    assertEquals(inputVariables, getField(requestTask, "headerOutputVariables"));
  }

  @Test
  void getRequestWorksTest() {
    setField(requestTask, "request", embeddedRequest);

    assertEquals(embeddedRequest, requestTask.getRequest());
  }

  @Test
  void setRequestWorksTest() {
    setField(requestTask, "request", null);

    requestTask.setRequest(embeddedRequest);
    assertEquals(embeddedRequest, getField(requestTask, "request"));
  }

}
