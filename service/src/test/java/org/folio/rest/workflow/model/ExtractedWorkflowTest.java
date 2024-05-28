package org.folio.rest.workflow.model;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

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
    assertTrue(EMPTY_STRING_MAP.equals(extractedWorkflow.getNodes()));
  }

}
