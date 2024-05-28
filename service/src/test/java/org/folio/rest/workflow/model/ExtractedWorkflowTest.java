package org.folio.rest.workflow.model;

import static org.folio.rest.workflow.model.ExtractedWorkflow.VERSION_PATTERN_1_0;
import static org.folio.spring.test.mock.MockMvcConstant.NULL_STR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ExtractedWorkflowTest {

  private static final List<String> EMPTY_LIST = new ArrayList<>();

  private static final Map<String, JsonNode> EMPTY_JSON_MAP = new HashMap<>();

  private static final Map<String, String> EMPTY_STRING_MAP = new HashMap<>();

  private ExtractedWorkflow extractedWorkflow;

  @Test
  void instantiationWorks() {
    extractedWorkflow = new ExtractedWorkflow();

    assertIterableEquals(EMPTY_LIST, extractedWorkflow.getExpanded());
    assertTrue(EMPTY_JSON_MAP.equals(extractedWorkflow.getRequired()));
    assertTrue(EMPTY_JSON_MAP.equals(extractedWorkflow.getNodes()));
    assertTrue(EMPTY_JSON_MAP.equals(extractedWorkflow.getTriggers()));
    assertTrue(EMPTY_STRING_MAP.equals(extractedWorkflow.getScripts()));
  }

  @ParameterizedTest
  @MethodSource("patternMatchFor")
  void patternMatchWorks(String version, boolean expect) {
    assertEquals(expect, VERSION_PATTERN_1_0.matcher(version).find());
  }

  /**
   * Helper function for parameterized tests for detectFormat().
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - String version The version string to validate.
   *     - boolean expect The expected result.
   */
  private static Stream<Arguments> patternMatchFor() {
    return Stream.of(
      Arguments.of("",                  false),
      Arguments.of("5",                 false),
      Arguments.of("1.0",               true),
      Arguments.of("1.0.0",             true),
      Arguments.of("1.0.1",             true),
      Arguments.of("1.0.12345",         true),
      Arguments.of("1.1",               false),
      Arguments.of("1.1.0",             false),
      Arguments.of("1.1.1",             false),
      Arguments.of("1.1.12345",         false),
      Arguments.of("2.1",               false),
      Arguments.of("2.1.0",             false),
      Arguments.of("2.1.1",             false),
      Arguments.of("2.1.12345",         false),
      Arguments.of("888.888.888",       false),
      Arguments.of("not a number",      false),
      Arguments.of("not 1.0.0",         false),
      Arguments.of("1.0.0 ok, unideal", true)
    );
  }

}
