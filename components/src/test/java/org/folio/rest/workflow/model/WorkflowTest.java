package org.folio.rest.workflow.model;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorkflowTest {

  @Mock
  private Setup setup;

  @Mock
  private Node node;

  private List<Node> nodes;

  private Workflow workflow;

  @BeforeEach
  void beforeEach() {
    workflow = new Workflow();
    nodes = new ArrayList<>();
    nodes.add(node);
  }

  @Test
  void getIdWorksTest() {
    setField(workflow, "id", VALUE);

    assertEquals(VALUE, workflow.getId());
  }

  @Test
  void setIdWorksTest() {
    setField(workflow, "id", null);

    workflow.setId(VALUE);
    assertEquals(VALUE, getField(workflow, "id"));
  }

  @Test
  void getNameWorksTest() {
    setField(workflow, "name", VALUE);

    assertEquals(VALUE, workflow.getName());
  }

  @Test
  void setNameWorksTest() {
    setField(workflow, "name", null);

    workflow.setName(VALUE);
    assertEquals(VALUE, getField(workflow, "name"));
  }

  @Test
  void getDescriptionWorksTest() {
    setField(workflow, "description", VALUE);

    assertEquals(VALUE, workflow.getDescription());
  }

  @Test
  void setDescriptionWorksTest() {
    setField(workflow, "description", null);

    workflow.setDescription(VALUE);
    assertEquals(VALUE, getField(workflow, "description"));
  }

  @Test
  void getNodesWorksTest() {
    setField(workflow, "nodes", nodes);

    assertEquals(nodes, workflow.getNodes());
  }

  @Test
  void setNodesWorksTest() {
    setField(workflow, "nodes", null);

    workflow.setNodes(nodes);
    assertEquals(nodes, getField(workflow, "nodes"));
  }

  @Test
  void getVersionTagWorksTest() {
    setField(workflow, "versionTag", VALUE);

    assertEquals(VALUE, workflow.getVersionTag());
  }

  @Test
  void setVersionTagWorksTest() {
    setField(workflow, "versionTag", null);

    workflow.setVersionTag(VALUE);
    assertEquals(VALUE, getField(workflow, "versionTag"));
  }

  @Test
  void getHistoryTimeToLiveWorksTest() {
    setField(workflow, "historyTimeToLive", 1);

    assertEquals(1, workflow.getHistoryTimeToLive());
  }

  @Test
  void setHistoryTimeToLiveWorksTest() {
    setField(workflow, "historyTimeToLive", null);

    workflow.setHistoryTimeToLive(1);
    assertEquals(1, getField(workflow, "historyTimeToLive"));
  }

  @Test
  void getActiveWorksTest() {
    setField(workflow, "active", true);

    assertEquals(true, workflow.isActive());
  }

  @Test
  void setActiveWorksTest() {
    setField(workflow, "active", false);

    workflow.setActive(true);
    assertEquals(true, getField(workflow, "active"));
  }

  @Test
  void getDeploymentIdWorksTest() {
    setField(workflow, "deploymentId", VALUE);

    assertEquals(VALUE, workflow.getDeploymentId());
  }

  @Test
  void setDeploymentIdWorksTest() {
    setField(workflow, "deploymentId", null);

    workflow.setDeploymentId(VALUE);
    assertEquals(VALUE, getField(workflow, "deploymentId"));
  }

  @Test
  void getSetupWorksTest() {
    setField(workflow, "setup", setup);

    assertEquals(setup, workflow.getSetup());
  }

  @Test
  void setSetupWorksTest() {
    setField(workflow, "setup", null);

    workflow.setSetup(setup);
    assertEquals(setup, getField(workflow, "setup"));
  }

}
