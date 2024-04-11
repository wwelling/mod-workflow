package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.folio.rest.workflow.enums.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmbeddedRequestTest {

  private EmbeddedRequest embeddedRequest;

  @BeforeEach
  void beforeEach() {
    embeddedRequest = new EmbeddedRequest();
  }

  @Test
  void getUrlWorksTest() {
    setField(embeddedRequest, "url", VALUE);

    assertEquals(VALUE, embeddedRequest.getUrl());
  }

  @Test
  void setUrlWorksTest() {
    setField(embeddedRequest, "url", null);

    embeddedRequest.setUrl(VALUE);
    assertEquals(VALUE, getField(embeddedRequest, "url"));
  }

  @Test
  void getMethodWorksTest() {
    setField(embeddedRequest, "method", HttpMethod.DELETE);

    assertEquals(HttpMethod.DELETE, embeddedRequest.getMethod());
  }

  @Test
  void setMethodWorksTest() {
    setField(embeddedRequest, "method", null);

    embeddedRequest.setMethod(HttpMethod.DELETE);
    assertEquals(HttpMethod.DELETE, getField(embeddedRequest, "method"));
  }

  @Test
  void getContentTypeWorksTest() {
    setField(embeddedRequest, "contentType", VALUE);

    assertEquals(VALUE, embeddedRequest.getContentType());
  }

  @Test
  void setContentTypeWorksTest() {
    setField(embeddedRequest, "contentType", null);

    embeddedRequest.setContentType(VALUE);
    assertEquals(VALUE, getField(embeddedRequest, "contentType"));
  }

  @Test
  void getAcceptWorksTest() {
    setField(embeddedRequest, "accept", VALUE);

    assertEquals(VALUE, embeddedRequest.getAccept());
  }

  @Test
  void setAcceptWorksTest() {
    setField(embeddedRequest, "accept", null);

    embeddedRequest.setAccept(VALUE);
    assertEquals(VALUE, getField(embeddedRequest, "accept"));
  }

  @Test
  void getBodyTemplateWorksTest() {
    setField(embeddedRequest, "bodyTemplate", VALUE);

    assertEquals(VALUE, embeddedRequest.getBodyTemplate());
  }

  @Test
  void setBodyTemplateWorksTest() {
    setField(embeddedRequest, "bodyTemplate", null);

    embeddedRequest.setBodyTemplate(VALUE);
    assertEquals(VALUE, getField(embeddedRequest, "bodyTemplate"));
  }

  @Test
  void getIterableWorksTest() {
    setField(embeddedRequest, "iterable", true);

    assertEquals(true, embeddedRequest.isIterable());
  }

  @Test
  void setIterableWorksTest() {
    setField(embeddedRequest, "iterable", false);

    embeddedRequest.setIterable(true);
    assertEquals(true, getField(embeddedRequest, "iterable"));
  }

  @Test
  void getResponseKeyWorksTest() {
    setField(embeddedRequest, "responseKey", VALUE);

    assertEquals(VALUE, embeddedRequest.getResponseKey());
  }

  @Test
  void setResponseKeyWorksTest() {
    setField(embeddedRequest, "responseKey", null);

    embeddedRequest.setResponseKey(VALUE);
    assertEquals(VALUE, getField(embeddedRequest, "responseKey"));
  }

}
