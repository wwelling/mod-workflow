package org.folio.rest.workflow.service;

import org.folio.rest.workflow.exception.WorkflowEngineServiceException;
import org.folio.rest.workflow.model.Workflow;
import org.folio.rest.workflow.model.repo.WorkflowRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class WorkflowEngineService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowEngineService.class);

  private static final String WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE = "%s%s/workflow-engine/workflows/activate";
  private static final String WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE = "%s%s/workflow-engine/workflows/deactivate";

  private static final String PROCESS_DEFINITION_START_URL_TEMPLATE = "%s%s/process-definition/%s/start";
  private static final String PROCESS_DEFINITION_GET_URL_TEMPLATE = "%s%s/process-definition%s";

  private static final String HISTORY_PROCESS_INSTANCE_URL_TEMPLATE = "%s%s/history/process-instance%s";
  private static final String HISTORY_INCIDENT_URL_TEMPLATE = "%s%s/history/incident%s";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.auth.tokenHeaderName:X-Okapi-Token}")
  private String tokenHeaderName;

  @Value("${okapi.url}")
  private String okapiUrl;

  @Value("${okapi.camunda.base-path}")
  private String basePath;

  @Value("${okapi.camunda.rest-path}")
  private String restPath;

  @Autowired
  private WorkflowRepo workflowRepo;

  @Autowired
  ObjectMapper mapper;

  private RestTemplate restTemplate;

  public WorkflowEngineService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Workflow activate(String workflowId, String tenant, String token)
      throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);
    return sendWorkflowRequest(workflow, WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE, tenant, token);
  }

  public Workflow deactivate(String workflowId, String tenant, String token)
      throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);
    return sendWorkflowRequest(workflow, WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE, tenant, token);
  }

  public JsonNode start(String workflowId, String tenant, String token, JsonNode context)
      throws WorkflowEngineServiceException {

    Workflow workflow = workflowRepo.getOne(workflowId);
    String id = workflow.getDeploymentId();
    String version = workflow.getVersionTag();

    JsonNode definition = fetchDeploymentDefinition(id, version, tenant, token);
    String definitionId = definition.get("id").asText();

    HttpEntity<JsonNode> contextHttpEntity = new HttpEntity<>(context, headers(tenant, token));

    String url = String.format(PROCESS_DEFINITION_START_URL_TEMPLATE, okapiUrl, restPath, definitionId);
    ResponseEntity<JsonNode> response = exchange(url, HttpMethod.POST, contextHttpEntity, JsonNode.class);

    return response.getBody();
  }

  public JsonNode history(String workflowId, String tenant, String token) throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);
    String deploymentId = workflow.getDeploymentId();
    String version = workflow.getVersionTag();

    JsonNode processDefinition = fetchDeploymentDefinition(deploymentId, version, tenant, token);
    String processDefinitionId = processDefinition.get("id").asText();

    ArrayNode instances = fetchProcessInstanceHistory(processDefinitionId, tenant, token);

    instances.forEach(instance -> {
      String processInstanceId = instance.get("id").asText();

      ((ObjectNode) instance).withArray("incidents")
        .addAll(fetchIncidentsHistory(processInstanceId, tenant, token));
    });

    return instances;
  }

  private JsonNode fetchDeploymentDefinition(String deploymentId, String version, String tenant, String token)
      throws WorkflowEngineServiceException {

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers(tenant, token));

    String arguments = String.format("?deploymentId=%s&versionTag=%s&maxResults=1", deploymentId, version);
    String url = String.format(PROCESS_DEFINITION_GET_URL_TEMPLATE, okapiUrl, restPath, arguments);
    ResponseEntity<ArrayNode> response = exchange(url, HttpMethod.GET, httpEntity, ArrayNode.class);

    ArrayNode definitions = response.getBody();
    if (response.getStatusCode() == HttpStatus.OK && definitions != null && !definitions.isEmpty()) {
      logger.debug("Response body: {}", definitions);

      return definitions.get(0);
    }

    throw new WorkflowEngineServiceException("Unable to get workflow process definition from workflow engine!");
  }

  private ArrayNode fetchProcessInstanceHistory(String processDefinitionId, String tenant, String token)
      throws WorkflowEngineServiceException {

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers(tenant, token));

    String arguments = String.format("?processDefinitionId=%s&sortBy=startTime&sortOrder=asc", processDefinitionId);
    String url = String.format(HISTORY_PROCESS_INSTANCE_URL_TEMPLATE, okapiUrl, restPath, arguments);
    ResponseEntity<ArrayNode> response = exchange(url, HttpMethod.GET, httpEntity, ArrayNode.class);

    ArrayNode definitions = response.getBody();
    if (response.getStatusCode() == HttpStatus.OK && definitions != null) {
      logger.debug("Response body: {}", definitions);

      return definitions;
    }

    throw new WorkflowEngineServiceException("Unable to get workflow process instance history from workflow engine!");
  }

  private ArrayNode fetchIncidentsHistory(String processInstanceId, String tenant, String token) {

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers(tenant, token));

    String arguments = String.format("?processInstanceId=%s&sortBy=createTime&sortOrder=asc", processInstanceId);
    String url = String.format(HISTORY_INCIDENT_URL_TEMPLATE, okapiUrl, restPath, arguments);
    ResponseEntity<ArrayNode> response = exchange(url, HttpMethod.GET, httpEntity, ArrayNode.class);

    ArrayNode incidents = response.getBody();
    if (response.getStatusCode() == HttpStatus.OK && incidents != null) {
      logger.debug("Response body: {}", incidents);
    }
    else {
      logger.debug("Unable to get workflow incidents history from workflow engine!");

      incidents = mapper.createArrayNode();
    }

    return incidents;
  }

  private Workflow sendWorkflowRequest(Workflow workflow, String requestPath, String tenant, String token)
      throws WorkflowEngineServiceException {

    HttpEntity<Workflow> workflowHttpEntity = new HttpEntity<>(workflow, headers(tenant, token));

    String url = String.format(requestPath, okapiUrl, basePath);
    ResponseEntity<Workflow> response = exchange(url, HttpMethod.POST, workflowHttpEntity, Workflow.class);

    Workflow responseWorkflow = response.getBody();
    if (response.getStatusCode() == HttpStatus.OK && responseWorkflow != null) {
      logger.debug("Response body: {}", responseWorkflow);

      String deploymentId = responseWorkflow.getDeploymentId();
      logger.info("Workflow is active = {}, deploymentID = {}", responseWorkflow.isActive(), deploymentId);
      return workflowRepo.save(responseWorkflow);
    }

    throw new WorkflowEngineServiceException("Unable to get updated workflow from workflow engine!");
  }

  private HttpHeaders headers(String tenant, String token) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add(tenantHeaderName, tenant);
    requestHeaders.add(tokenHeaderName, token);
    requestHeaders.add("Content-Type", "application/json");
    return requestHeaders;
  }

  private <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> request, Class<T> responseType) {
    return this.restTemplate.exchange(url, method, request, responseType, (Object[]) new String[0]);
  }

}
