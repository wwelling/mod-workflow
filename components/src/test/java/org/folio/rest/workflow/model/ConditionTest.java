package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConditionTest {

  private Condition condition;

  @BeforeEach
  void beforeEach() {
    condition = new Condition();
  }

  @Test
  void getIdWorksTest() {
    setField(condition, "id", VALUE);

    assertEquals(VALUE, condition.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(condition, "id", null);

    condition.setId(VALUE);
    assertEquals(VALUE, getField(condition, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(condition, "name", VALUE);

    assertEquals(VALUE, condition.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(condition, "name", null);

    condition.setName(VALUE);
    assertEquals(VALUE, getField(condition, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(condition, "description", VALUE);

    assertEquals(VALUE, condition.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(condition, "description", null);

    condition.setDescription(VALUE);
    assertEquals(VALUE, getField(condition, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(condition, "deserializeAs", VALUE);

    assertEquals(VALUE, condition.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(condition, "deserializeAs", null);

    condition.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(condition, "deserializeAs"));
  }

  @Test
  void getExpressionWorksTest() {
    setField(condition, "expression", VALUE);

    assertEquals(VALUE, condition.getExpression());
  }

  @Test
  void setExpressionWorksTest() {
    setField(condition, "expression", null);

    condition.setExpression(VALUE);
    assertEquals(VALUE, getField(condition, "expression"));
  }

  @Test
  void getAnswerWorksTest() {
    setField(condition, "answer", VALUE);

    assertEquals(VALUE, condition.getAnswer());
  }

  @Test
  void setAnswerWorksTest() {
    setField(condition, "answer", null);

    condition.setAnswer(VALUE);
    assertEquals(VALUE, getField(condition, "answer"));
  }

}
