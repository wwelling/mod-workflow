package org.folio.rest.workflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

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

@Service
public class WorkflowEngineService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowEngineService.class);

  private static final String WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE = "%s/workflow-engine/workflows/activate";
  private static final String WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE = "%s/workflow-engine/workflows/deactivate";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.auth.tokenHeaderName:X-Okapi-Token}")
  private String tokenHeaderName;

  @Value("${okapi.url}")
  private String okapiUrl;

  @Autowired
  private WorkflowRepo workflowRepo;

  private RestTemplate restTemplate;

  public WorkflowEngineService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Workflow activate(String workflowId, String tenant, String token) throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);
    return sendRequest(workflow, WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE, tenant, token);
  }

  public Workflow deactivate(String workflowId, String tenant, String token) throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);
    return sendRequest(workflow, WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE, tenant, token);
  }

  public JsonNode start(String workflowId, String tenant, String token, JsonNode context) throws WorkflowEngineServiceException {
    Workflow workflow = workflowRepo.getOne(workflowId);

    JsonNode definition = fetchDefinition(workflow.getDeploymentId(), tenant, token);
    String definitionId = definition.get("id").asText();

    HttpEntity<JsonNode> contextHttpEntity = new HttpEntity<>(context, headers(tenant, token));

    String url = String.format("%s/camunda/process-definition/%s/start", okapiUrl, definitionId);
    ResponseEntity<JsonNode> response = exchange(url, HttpMethod.POST, contextHttpEntity, JsonNode.class);

    return response.getBody();
  }

  private JsonNode fetchDefinition(String deploymentId, String tenant, String token) throws WorkflowEngineServiceException {
    String definitionsUrl = String.format("%s/camunda/process-definition?deploymentId=%s", okapiUrl, deploymentId);
    ResponseEntity<ArrayNode> response = this.restTemplate.getForEntity(definitionsUrl, ArrayNode.class);

    if (response.getStatusCode() == HttpStatus.OK && !response.getBody().isEmpty()) {
      logger.debug("Response body: {}", response.getBody());

      try {
        return response.getBody().get(0);
      } catch (Exception e) {

      }
    }

    throw new WorkflowEngineServiceException("Unable to get workflow process definition from workflow engine!");
  }

  private Workflow sendRequest(Workflow workflow, String requestPath, String tenant, String token)
      throws WorkflowEngineServiceException {

    HttpEntity<Workflow> workflowHttpEntity = new HttpEntity<>(workflow, headers(tenant, token));

    String url = String.format(requestPath, okapiUrl);
    ResponseEntity<Workflow> response = exchange(url, HttpMethod.POST, workflowHttpEntity, Workflow.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      logger.debug("Response body: {}", response.getBody());

      try {
        Workflow responseWorkflow = response.getBody();
        String deploymentId = responseWorkflow.getDeploymentId();
        logger.info("Workflow is actvie = {}, deploymentID = {}", responseWorkflow.isActive(), deploymentId);
        return workflowRepo.save(responseWorkflow);
      } catch (Exception e) {

      }
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
