package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.folio.rest.workflow.enums.ScriptType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmbeddedProcessorTest {

  private EmbeddedProcessor embeddedProcessor;

  @BeforeEach
  void beforeEach() {
    embeddedProcessor = new EmbeddedProcessor();
  }

  @Test
  void getScriptTypeWorksTest() {
    setField(embeddedProcessor, "scriptType", ScriptType.GROOVY);

    assertEquals(ScriptType.GROOVY, embeddedProcessor.getScriptType());
  }

  @Test
  void setScriptTypeWorksTest() {
    setField(embeddedProcessor, "scriptType", null);

    embeddedProcessor.setScriptType(ScriptType.GROOVY);
    assertEquals(ScriptType.GROOVY, getField(embeddedProcessor, "scriptType"));
  }

  @Test
  void getFunctionNameWorksTest() {
    setField(embeddedProcessor, "functionName", VALUE);

    assertEquals(VALUE, embeddedProcessor.getFunctionName());
  }

  @Test
  void setFunctionNameWorksTest() {
    setField(embeddedProcessor, "functionName", null);

    embeddedProcessor.setFunctionName(VALUE);
    assertEquals(VALUE, getField(embeddedProcessor, "functionName"));
  }

  @Test
  void getCodeWorksTest() {
    setField(embeddedProcessor, "code", VALUE);

    assertEquals(VALUE, embeddedProcessor.getCode());
  }

  @Test
  void setCodeWorksTest() {
    setField(embeddedProcessor, "code", null);

    embeddedProcessor.setCode(VALUE);
    assertEquals(VALUE, getField(embeddedProcessor, "code"));
  }

  @Test
  void getBufferWorksTest() {
    setField(embeddedProcessor, "buffer", 1);

    assertEquals(1, embeddedProcessor.getBuffer());
  }

  @Test
  void setBufferWorksTest() {
    setField(embeddedProcessor, "buffer", 0);

    embeddedProcessor.setBuffer(1);
    assertEquals(1, getField(embeddedProcessor, "buffer"));
  }

  @Test
  void getDelayWorksTest() {
    setField(embeddedProcessor, "delay", 1);

    assertEquals(1, embeddedProcessor.getDelay());
  }

  @Test
  void setDelayWorksTest() {
    setField(embeddedProcessor, "delay", 0);

    embeddedProcessor.setDelay(1);
    assertEquals(1, getField(embeddedProcessor, "delay"));
  }

}
