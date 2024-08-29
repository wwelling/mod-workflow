package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import org.folio.rest.workflow.enums.InputAttribute;
import org.folio.rest.workflow.enums.InputType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmbeddedInputTest {

  private List<InputAttribute> attributes;

  private List<String> options;

  private EmbeddedInput embeddedInput;

  @BeforeEach
  void beforeEach() {
    attributes = new ArrayList<>();
    options = new ArrayList<>();

    embeddedInput = new EmbeddedInput();
  }

  @Test
  void getAttributesWorksTest() {
    setField(embeddedInput, "attributes", attributes);

    assertEquals(attributes, embeddedInput.getAttributes());
  }

  @Test
  void setAttributesWorksTest() {
    setField(embeddedInput, "attributes", null);

    embeddedInput.setAttributes(attributes);
    assertEquals(attributes, getField(embeddedInput, "attributes"));
  }

  @Test
  void getDefaultValueWorksTest() {
    setField(embeddedInput, "defaultValue", VALUE);

    assertEquals(VALUE, embeddedInput.getDefaultValue());
  }

  @Test
  void setDefaultValueWorksTest() {
    setField(embeddedInput, "defaultValue", null);

    embeddedInput.setDefaultValue(VALUE);
    assertEquals(VALUE, getField(embeddedInput, "defaultValue"));
  }

  @Test
  void getFieldIdWorksTest() {
    setField(embeddedInput, "fieldId", VALUE);

    assertEquals(VALUE, embeddedInput.getFieldId());
  }

  @Test
  void setFieldIdWorksTest() {
    setField(embeddedInput, "fieldId", null);

    embeddedInput.setFieldId(VALUE);
    assertEquals(VALUE, getField(embeddedInput, "fieldId"));
  }

  @Test
  void getFieldLabelWorksTest() {
    setField(embeddedInput, "fieldLabel", VALUE);

    assertEquals(VALUE, embeddedInput.getFieldLabel());
  }

  @Test
  void setFieldLabelWorksTest() {
    setField(embeddedInput, "fieldLabel", null);

    embeddedInput.setFieldLabel(VALUE);
    assertEquals(VALUE, getField(embeddedInput, "fieldLabel"));
  }

  @Test
  void getInputTypeWorksTest() {
    setField(embeddedInput, "inputType", InputType.EMAIL);

    assertEquals(InputType.EMAIL, embeddedInput.getInputType());
  }

  @Test
  void setInputTypeWorksTest() {
    setField(embeddedInput, "inputType", null);

    embeddedInput.setInputType(InputType.CHECKBOX);
    assertEquals(InputType.CHECKBOX, getField(embeddedInput, "inputType"));
  }

  @Test
  void getOptionsWorksTest() {
    setField(embeddedInput, "options", options);

    assertEquals(options, embeddedInput.getOptions());
  }

  @Test
  void setOptionsWorksTest() {
    setField(embeddedInput, "options", null);

    embeddedInput.setOptions(options);
    assertEquals(options, getField(embeddedInput, "options"));
  }

  @Test
  void getRequiredWorksTest() {
    setField(embeddedInput, "required", true);

    assertEquals(true, embeddedInput.getRequired());
  }

  @Test
  void setRequiredWorksTest() {
    setField(embeddedInput, "required", false);

    embeddedInput.setRequired(true);
    assertEquals(true, getField(embeddedInput, "required"));
  }

}
