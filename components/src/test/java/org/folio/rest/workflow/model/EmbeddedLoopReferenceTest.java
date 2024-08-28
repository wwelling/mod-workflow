package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmbeddedLoopReferenceTest {

  private EmbeddedLoopReference embeddedLoopReference;

  @BeforeEach
  void beforeEach() {
    embeddedLoopReference = new EmbeddedLoopReference();
  }

  @Test
  void getCardinalityExpressionWorksTest() {
    setField(embeddedLoopReference, "cardinalityExpression", VALUE);

    assertEquals(VALUE, embeddedLoopReference.getCardinalityExpression());
  }

  @Test
  void setCardinalityExpressionWorksTest() {
    setField(embeddedLoopReference, "cardinalityExpression", null);

    embeddedLoopReference.setCardinalityExpression(VALUE);
    assertEquals(VALUE, getField(embeddedLoopReference, "cardinalityExpression"));
  }

  @Test
  void getDataInputRefExpressionWorksTest() {
    setField(embeddedLoopReference, "dataInputRefExpression", VALUE);

    assertEquals(VALUE, embeddedLoopReference.getDataInputRefExpression());
  }

  @Test
  void setDataInputRefExpressionWorksTest() {
    setField(embeddedLoopReference, "dataInputRefExpression", null);

    embeddedLoopReference.setDataInputRefExpression(VALUE);
    assertEquals(VALUE, getField(embeddedLoopReference, "dataInputRefExpression"));
  }

  @Test
  void getInputDataNameWorksTest() {
    setField(embeddedLoopReference, "inputDataName", VALUE);

    assertEquals(VALUE, embeddedLoopReference.getInputDataName());
  }

  @Test
  void setInputDataNameWorksTest() {
    setField(embeddedLoopReference, "inputDataName", null);

    embeddedLoopReference.setInputDataName(VALUE);
    assertEquals(VALUE, getField(embeddedLoopReference, "inputDataName"));
  }

  @Test
  void getCompleteConditionExpressionWorksTest() {
    setField(embeddedLoopReference, "completeConditionExpression", VALUE);

    assertEquals(VALUE, embeddedLoopReference.getCompleteConditionExpression());
  }

  @Test
  void setCompleteConditionExpressionWorksTest() {
    setField(embeddedLoopReference, "completeConditionExpression", null);

    embeddedLoopReference.setCompleteConditionExpression(VALUE);
    assertEquals(VALUE, getField(embeddedLoopReference, "completeConditionExpression"));
  }

  @Test
  void getParallelWorksTest() {
    setField(embeddedLoopReference, "parallel", true);

    assertEquals(true, embeddedLoopReference.getParallel());
  }

  @Test
  void setParallelWorksTest() {
    setField(embeddedLoopReference, "parallel", false);

    embeddedLoopReference.setParallel(true);
    assertEquals(true, getField(embeddedLoopReference, "parallel"));
  }

}
