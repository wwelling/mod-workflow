package org.folio.rest.workflow.controller;

import static org.folio.spring.test.mock.MockMvcConstant.APP_JSON;
import static org.folio.spring.test.mock.MockMvcConstant.APP_RAML;
import static org.folio.spring.test.mock.MockMvcConstant.APP_SCHEMA;
import static org.folio.spring.test.mock.MockMvcConstant.APP_STAR;
import static org.folio.spring.test.mock.MockMvcConstant.APP_STREAM;
import static org.folio.spring.test.mock.MockMvcConstant.JSON_OBJECT;
import static org.folio.spring.test.mock.MockMvcConstant.KEY;
import static org.folio.spring.test.mock.MockMvcConstant.MT_APP_JSON;
import static org.folio.spring.test.mock.MockMvcConstant.NO_PARAM;
import static org.folio.spring.test.mock.MockMvcConstant.NULL_STR;
import static org.folio.spring.test.mock.MockMvcConstant.OKAPI_HEAD_NO_URL;
import static org.folio.spring.test.mock.MockMvcConstant.OKAPI_HEAD_TENANT;
import static org.folio.spring.test.mock.MockMvcConstant.OKAPI_HEAD_TOKEN;
import static org.folio.spring.test.mock.MockMvcConstant.PLAIN_BODY;
import static org.folio.spring.test.mock.MockMvcConstant.STAR;
import static org.folio.spring.test.mock.MockMvcConstant.TEXT_PLAIN;
import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.folio.spring.test.mock.MockMvcReflection.DELETE;
import static org.folio.spring.test.mock.MockMvcReflection.PATCH;
import static org.folio.spring.test.mock.MockMvcReflection.POST;
import static org.folio.spring.test.mock.MockMvcReflection.PUT;
import static org.folio.spring.test.mock.MockMvcRequest.appendBody;
import static org.folio.spring.test.mock.MockMvcRequest.appendHeaders;
import static org.folio.spring.test.mock.MockMvcRequest.appendParameters;
import static org.folio.spring.test.mock.MockMvcRequest.buildArguments1;
import static org.folio.spring.test.mock.MockMvcRequest.buildArguments2;
import static org.folio.spring.test.mock.MockMvcRequest.invokeRequestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.folio.rest.workflow.service.WorkflowCqlService;
import org.folio.rest.workflow.service.WorkflowEngineService;
import org.folio.spring.tenant.properties.TenantProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

@WebMvcTest(WorkflowController.class)
@ExtendWith(MockitoExtension.class)
class WorkflowControllerTest {

  private static final String PATH = "/workflows";

  private static final String PATH_SEARCH = PATH + "/search";

  private static final String OFFSET = "offset";

  private static final String OFFSET_VALUE = "1";

  private static final String LIMIT = "limit";

  private static final String LIMIT_VALUE = "100";

  private static final String QUERY = "query";

  private static final String QUERY_VALUE = "cql.allRecords=1";

  private static final MultiValueMap<String, String> LIM_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    LIMIT, List.of(LIMIT_VALUE)
  ));

  private static final MultiValueMap<String, String> OFF_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    OFFSET, List.of(OFFSET_VALUE)
  ));

  private static final MultiValueMap<String, String> OFF_LIM_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    LIMIT, List.of(LIMIT_VALUE), OFFSET, List.of(OFFSET_VALUE)
  ));

  private static final MultiValueMap<String, String> QUE_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    QUERY, List.of(QUERY_VALUE)
  ));

  private static final MultiValueMap<String, String> QUE_LIM_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    QUERY, List.of(QUERY_VALUE),
    LIMIT, List.of(LIMIT_VALUE)
  ));

  private static final MultiValueMap<String, String> QUE_OFF_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    QUERY, List.of(QUERY_VALUE),
    OFFSET, List.of(OFFSET_VALUE)
  ));

  private static final MultiValueMap<String, String> QUE_OFF_LIM_PARAM = CollectionUtils.toMultiValueMap(Map.of(
    QUERY, List.of(QUERY_VALUE),
    OFFSET, List.of(OFFSET_VALUE),
    LIMIT, List.of(LIMIT_VALUE)
  ));

  private MockMvc mvc;

  @Autowired
  protected ObjectMapper mapper;

  @Autowired
  private WorkflowController workflowController;

  @MockBean
  private WorkflowCqlService workflowCqlService;

  @MockBean
  private WorkflowEngineService workflowEngineService;

  @MockBean
  private TenantProperties tenantProperties;

  @BeforeEach
  void beforeEach() {
    mvc = MockMvcBuilders.standaloneSetup(workflowController).build();
  }

  @ParameterizedTest
  @MethodSource("provideHeadersBodyStatusForSearchWorkflows")
  void searchWorkflowsTest(HttpHeaders headers, String contentType, String accept, MediaType mediaType, MultiValueMap<String, String> parameters, String body, int status) throws Exception {
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put(KEY, VALUE);

    lenient().when(workflowCqlService.findByCql(anyString(), anyLong(), anyInt())).thenReturn(objectNode);

    MockHttpServletRequestBuilder request = appendHeaders(get(PATH_SEARCH), headers, contentType, accept);
    request = appendParameters(request, parameters);

    MvcResult result = mvc.perform(appendBody(request, body))
      .andDo(log()).andExpect(status().is(status)).andReturn();

    if (status == 200) {
      MediaType responseType = MediaType.parseMediaType(result.getResponse().getContentType());

      assertTrue(mediaType.isCompatibleWith(responseType));
      assertEquals(objectNode.toString(), result.getResponse().getContentAsString());
    }
  }

  @ParameterizedTest
  @MethodSource("provideDeletePatchPostPutForSearchWorkflows")
  void searchWorkflowsFailsTest(Method method, HttpHeaders headers, String contentType, String accept, MediaType mediaType, MultiValueMap<String, String> parameters, String body, int status) throws Exception {
    mvc.perform(invokeRequestBuilder(PATH_SEARCH, method, headers, contentType, accept, parameters, body))
      .andDo(log()).andExpect(status().is(status));
  }

  /**
   * Helper function for parameterized test providing DELETE, PATCH, POST, and PUT.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - Method The (reflection) request method.
   *     - HttpHeaders headers.
   *     - String contentType (Content-Type request).
   *     - String accept (ask for this Content-Type on response).
   *       String mediaType (response Content-Type).
   *     - MultiValueMap<String, String> parameters.
   *     - String body (request body).
   *     - int status (response HTTP status code).
   *
   * @throws SecurityException
   * @throws NoSuchMethodException
   */
  private static Stream<Arguments> provideDeletePatchPostPutForSearchWorkflows() throws NoSuchMethodException, SecurityException {
    String[] contentTypes = { APP_JSON, TEXT_PLAIN, APP_STREAM };
    String[] bodys = { JSON_OBJECT, PLAIN_BODY, JSON_OBJECT };
    String[] accepts = { APP_RAML, APP_SCHEMA, APP_JSON, TEXT_PLAIN, APP_STREAM, NULL_STR, APP_STAR, STAR };
    MediaType[] mediaTypes = { MT_APP_JSON };
    Object[] params = { NO_PARAM, LIM_PARAM, OFF_LIM_PARAM, OFF_PARAM };

    Stream<Arguments> stream1 = buildArguments2(DELETE, OKAPI_HEAD_NO_URL, contentTypes, accepts, mediaTypes, params, bodys, 405);
    Stream<Arguments> stream2 = buildArguments2(PATCH, OKAPI_HEAD_NO_URL, contentTypes, accepts, mediaTypes, params, bodys, 405);
    Stream<Arguments> stream = Stream.concat(stream1, stream2);

    stream1 = buildArguments2(POST, OKAPI_HEAD_NO_URL, contentTypes, accepts, mediaTypes, params, bodys, 405);
    stream2 = Stream.concat(stream, stream1);

    stream1 = buildArguments2(PUT, OKAPI_HEAD_NO_URL, contentTypes, accepts, mediaTypes, params, bodys, 405);
    return Stream.concat(stream2, stream1);
  }

  /**
   * Helper function for parameterized test providing tests with headers, body, and status for search workflows end point.
   *
   * This is intended to be used for when the correct HTTP method is being used in the request.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - HttpHeaders headers.
   *     - String contentType (Content-Type request).
   *     - String accept (ask for this Content-Type on response).
   *       String mediaType (response Content-Type).
   *     - MultiValueMap<String, String> parameters.
   *     - String body (request body).
   *     - int status (response HTTP status code).
   *
   * @throws SecurityException
   * @throws NoSuchMethodException
   */
  private static Stream<Arguments> provideHeadersBodyStatusForSearchWorkflows() throws NoSuchMethodException, SecurityException {
    Stream<Arguments> stream1 = Stream.of(
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_SCHEMA, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_JSON,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   TEXT_PLAIN, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STREAM, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   NULL_STR,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STAR,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   STAR,       MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, APP_SCHEMA, MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  406),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, APP_JSON,   MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  200),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, TEXT_PLAIN, MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  406),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, APP_STREAM, MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  406),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, NULL_STR,   MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  200),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, STAR,       MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  200),
      Arguments.of(OKAPI_HEAD_TENANT, TEXT_PLAIN, APP_STAR,   MT_APP_JSON, QUE_PARAM,         PLAIN_BODY,  200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, APP_SCHEMA, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, APP_JSON,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, TEXT_PLAIN, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, APP_STREAM, MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, NULL_STR,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, APP_STAR,   MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_STREAM, STAR,       MT_APP_JSON, QUE_PARAM,         JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_SCHEMA, MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_JSON,   MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   TEXT_PLAIN, MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STREAM, MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   NULL_STR,   MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STAR,   MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   STAR,       MT_APP_JSON, QUE_LIM_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_SCHEMA, MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_JSON,   MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   TEXT_PLAIN, MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STREAM, MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   NULL_STR,   MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STAR,   MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   STAR,       MT_APP_JSON, QUE_OFF_LIM_PARAM, JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_SCHEMA, MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_JSON,   MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   TEXT_PLAIN, MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STREAM, MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 406),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   NULL_STR,   MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   APP_STAR,   MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 200),
      Arguments.of(OKAPI_HEAD_TENANT, APP_JSON,   STAR,       MT_APP_JSON, QUE_OFF_PARAM,     JSON_OBJECT, 200)
    );

    String[] contentTypes = { APP_JSON, TEXT_PLAIN, APP_STREAM };
    String[] bodys = { JSON_OBJECT, PLAIN_BODY, JSON_OBJECT };
    String[] accepts = { APP_JSON, NULL_STR, APP_STAR, STAR };
    String[] acceptsInvalid = { APP_SCHEMA, TEXT_PLAIN, APP_STREAM };
    MediaType[] mediaTypes = { MT_APP_JSON };
    Object[] params = { NO_PARAM, LIM_PARAM, OFF_LIM_PARAM, OFF_PARAM };

    Stream<Arguments> stream2 = buildArguments1(OKAPI_HEAD_TOKEN, contentTypes, acceptsInvalid, mediaTypes, params, bodys, 406);

    Stream<Arguments> stream = Stream.concat(stream1, stream2);

    stream1 = buildArguments1(OKAPI_HEAD_TOKEN, contentTypes, accepts, mediaTypes, params, bodys, 400);
    stream2 = Stream.concat(stream, stream1);

    stream = buildArguments1(OKAPI_HEAD_TENANT, contentTypes, accepts, mediaTypes, params, bodys, 400);
    stream1 = Stream.concat(stream, stream2);

    stream = buildArguments1(OKAPI_HEAD_TENANT, contentTypes, acceptsInvalid, mediaTypes, params, bodys, 406);
    return Stream.concat(stream, stream1);
  }

}
