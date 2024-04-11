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
class EmailTaskTest {

  @Mock
  private EmbeddedVariable embeddedVariable;

  private Set<EmbeddedVariable> inputVariables;

  private EmailTask emailTask;

  @BeforeEach
  void beforeEach() {
    emailTask = new EmailTask();
    inputVariables = new HashSet<>();
    inputVariables.add(embeddedVariable);
  }

  @Test
  void getIdWorksTest() {
    setField(emailTask, "id", VALUE);

    assertEquals(VALUE, emailTask.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(emailTask, "id", null);

    emailTask.setId(VALUE);
    assertEquals(VALUE, getField(emailTask, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(emailTask, "name", VALUE);

    assertEquals(VALUE, emailTask.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(emailTask, "name", null);

    emailTask.setName(VALUE);
    assertEquals(VALUE, getField(emailTask, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(emailTask, "description", VALUE);

    assertEquals(VALUE, emailTask.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(emailTask, "description", null);

    emailTask.setDescription(VALUE);
    assertEquals(VALUE, getField(emailTask, "description"));
  }

  @Test
  void getDeserializeAsWorksTest() {
    setField(emailTask, "deserializeAs", VALUE);

    assertEquals(VALUE, emailTask.getDeserializeAs());
  }

  @Test
  void setDeserializeAsWorksTest() {
    setField(emailTask, "deserializeAs", null);

    emailTask.setDeserializeAs(VALUE);
    assertEquals(VALUE, getField(emailTask, "deserializeAs"));
  }

  @Test
  void getInputVariablesWorksTest() {
    setField(emailTask, "inputVariables", inputVariables);

    assertEquals(inputVariables, emailTask.getInputVariables());
  }

  @Test
  void setInputVariablesWorksTest() {
    setField(emailTask, "inputVariables", null);

    emailTask.setInputVariables(inputVariables);
    assertEquals(inputVariables, getField(emailTask, "inputVariables"));
  }

  @Test
  void getOutputVariableWorksTest() {
    setField(emailTask, "outputVariable", embeddedVariable);

    assertEquals(embeddedVariable, emailTask.getOutputVariable());
  }

  @Test
  void setOutputVariableWorksTest() {
    setField(emailTask, "outputVariable", null);

    emailTask.setOutputVariable(embeddedVariable);
    assertEquals(embeddedVariable, getField(emailTask, "outputVariable"));
  }

  @Test
  void getAsyncBeforeWorksTest() {
    setField(emailTask, "asyncBefore", true);

    assertEquals(true, emailTask.isAsyncBefore());
  }

  @Test
  void setAsyncBeforeWorksTest() {
    setField(emailTask, "asyncBefore", false);

    emailTask.setAsyncBefore(true);
    assertEquals(true, getField(emailTask, "asyncBefore"));
  }

  @Test
  void getAsyncAfterWorksTest() {
    setField(emailTask, "asyncAfter", true);

    assertEquals(true, emailTask.isAsyncAfter());
  }

  @Test
  void setAsyncAfterWorksTest() {
    setField(emailTask, "asyncAfter", false);

    emailTask.setAsyncAfter(true);
    assertEquals(true, getField(emailTask, "asyncAfter"));
  }

  @Test
  void getMailToWorksTest() {
    setField(emailTask, "mailTo", VALUE);

    assertEquals(VALUE, emailTask.getMailTo());
  }

  @Test
  void setMailToWorksTest() {
    setField(emailTask, "mailTo", null);

    emailTask.setMailTo(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailTo"));
  }

  @Test
  void getMailCcWorksTest() {
    setField(emailTask, "mailCc", VALUE);

    assertEquals(VALUE, emailTask.getMailCc());
  }

  @Test
  void setMailCcWorksTest() {
    setField(emailTask, "mailCc", null);

    emailTask.setMailCc(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailCc"));
  }

  @Test
  void getMailBccWorksTest() {
    setField(emailTask, "mailBcc", VALUE);

    assertEquals(VALUE, emailTask.getMailBcc());
  }

  @Test
  void setMailBccWorksTest() {
    setField(emailTask, "mailBcc", null);

    emailTask.setMailBcc(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailBcc"));
  }

  @Test
  void getMailFromWorksTest() {
    setField(emailTask, "mailFrom", VALUE);

    assertEquals(VALUE, emailTask.getMailFrom());
  }

  @Test
  void setMailFromWorksTest() {
    setField(emailTask, "mailFrom", null);

    emailTask.setMailFrom(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailFrom"));
  }

  @Test
  void getMailSubjectWorksTest() {
    setField(emailTask, "mailSubject", VALUE);

    assertEquals(VALUE, emailTask.getMailSubject());
  }

  @Test
  void setMailSubjectWorksTest() {
    setField(emailTask, "mailSubject", null);

    emailTask.setMailSubject(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailSubject"));
  }

  @Test
  void getMailTextWorksTest() {
    setField(emailTask, "mailText", VALUE);

    assertEquals(VALUE, emailTask.getMailText());
  }

  @Test
  void setMailTextWorksTest() {
    setField(emailTask, "mailText", null);

    emailTask.setMailText(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailText"));
  }

  @Test
  void getMailMarkupWorksTest() {
    setField(emailTask, "mailMarkup", VALUE);

    assertEquals(VALUE, emailTask.getMailMarkup());
  }

  @Test
  void setMailMarkupWorksTest() {
    setField(emailTask, "mailMarkup", null);

    emailTask.setMailMarkup(VALUE);
    assertEquals(VALUE, getField(emailTask, "mailMarkup"));
  }

  @Test
  void getAttachmentPathWorksTest() {
    setField(emailTask, "attachmentPath", VALUE);

    assertEquals(VALUE, emailTask.getAttachmentPath());
  }

  @Test
  void setAttachmentPathWorksTest() {
    setField(emailTask, "attachmentPath", null);

    emailTask.setAttachmentPath(VALUE);
    assertEquals(VALUE, getField(emailTask, "attachmentPath"));
  }

  @Test
  void getIncludeAttachmentWorksTest() {
    setField(emailTask, "includeAttachment", VALUE);

    assertEquals(VALUE, emailTask.getIncludeAttachment());
  }

  @Test
  void setIncludeAttachmentWorksTest() {
    setField(emailTask, "includeAttachment", null);

    emailTask.setIncludeAttachment(VALUE);
    assertEquals(VALUE, getField(emailTask, "includeAttachment"));
  }

}
