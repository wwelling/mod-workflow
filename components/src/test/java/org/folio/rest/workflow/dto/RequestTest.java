package org.folio.rest.workflow.dto;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

class RequestTest {

  private Request request;

  @BeforeEach
  void beforeEach() {
    request = new Impl();
  }

  @Test
  void getUrlWorksTest() {
    setField(request, "url", VALUE);

    assertEquals(VALUE, request.getUrl());
  }

  @Test
  void setUrlWorksTest() {
    setField(request, "url", null);

    request.setUrl(VALUE);
    assertEquals(VALUE, getField(request, "url"));
  }

  @Test
  void getMethodWorksTest() {
    setField(request, "method", HttpMethod.DELETE);

    assertEquals(HttpMethod.DELETE, request.getMethod());
  }

  @Test
  void setMethodWorksTest() {
    setField(request, "method", null);

    request.setMethod(HttpMethod.DELETE);
    assertEquals(HttpMethod.DELETE, getField(request, "method"));
  }

  @Test
  void getContentTypeWorksTest() {
    setField(request, "contentType", VALUE);

    assertEquals(VALUE, request.getContentType());
  }

  @Test
  void setContentTypeWorksTest() {
    setField(request, "contentType", null);

    request.setContentType(VALUE);
    assertEquals(VALUE, getField(request, "contentType"));
  }

  @Test
  void getAcceptWorksTest() {
    setField(request, "accept", VALUE);

    assertEquals(VALUE, request.getAccept());
  }

  @Test
  void setAcceptWorksTest() {
    setField(request, "accept", null);

    request.setAccept(VALUE);
    assertEquals(VALUE, getField(request, "accept"));
  }

  @Test
  void getBodyTemplateWorksTest() {
    setField(request, "bodyTemplate", VALUE);

    assertEquals(VALUE, request.getBodyTemplate());
  }

  @Test
  void setBodyTemplateWorksTest() {
    setField(request, "bodyTemplate", null);

    request.setBodyTemplate(VALUE);
    assertEquals(VALUE, getField(request, "bodyTemplate"));
  }

  @Test
  void getIterableWorksTest() {
    setField(request, "iterable", true);

    assertEquals(true, request.isIterable());
  }

  @Test
  void setIterableWorksTest() {
    setField(request, "iterable", false);

    request.setIterable(true);
    assertEquals(true, getField(request, "iterable"));
  }

  @Test
  void getResponseKeyWorksTest() {
    setField(request, "responseKey", VALUE);

    assertEquals(VALUE, request.getResponseKey());
  }

  @Test
  void setResponseKeyWorksTest() {
    setField(request, "responseKey", null);

    request.setResponseKey(VALUE);
    assertEquals(VALUE, getField(request, "responseKey"));
  }

  private static class Impl extends Request { };

}
