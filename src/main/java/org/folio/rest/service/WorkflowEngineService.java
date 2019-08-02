package org.folio.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.folio.rest.exception.WorkflowEngineServiceException;
import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class WorkflowEngineService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowEngineService.class);

  private static final String WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE = "%s/workflow-engine/workflows/activate";
  private static final String WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE = "%s/workflow-engine/workflows/deactivate";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.auth.tokenHeaderName:X-Okapi-Token}")
  private String tokenHeaderName;

  @Value("${okapi.location}")
  private String okapiLocation;

  @Autowired
  private HttpService httpService;

  @Autowired
  private WorkflowRepo workflowRepo;

  public Workflow activate(Workflow workflow, String tenant, String token) throws WorkflowEngineServiceException {
    return sendRequest(workflow, WORKFLOW_ENGINE_ACTIVATE_URL_TEMPLATE, tenant, token);
  }

  public Workflow deactivate(Workflow workflow, String tenant, String token) throws WorkflowEngineServiceException {
    return sendRequest(workflow, WORKFLOW_ENGINE_DEACTIVATE_URL_TEMPLATE, tenant, token);
  }

  private Workflow sendRequest(Workflow workflow, String requestPath, String tenant, String token)
      throws WorkflowEngineServiceException {

    HttpHeaders tenantHeader = new HttpHeaders();
    tenantHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> tenantHttpEntity = new HttpEntity<>(tenant, tenantHeader);

    HttpHeaders deploymentNameHeader = new HttpHeaders();
    deploymentNameHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentNameHttpEntity = new HttpEntity<>(workflow.getName(), deploymentNameHeader);

    HttpHeaders deploymentSourceHeader = new HttpHeaders();
    deploymentSourceHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentSourceHttpEntity = new HttpEntity<>("process application", deploymentSourceHeader);

    MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
    parts.add("tenant-id", tenantHttpEntity);
    parts.add("deployment-name", deploymentNameHttpEntity);
    parts.add("deployment-source", deploymentSourceHttpEntity);

    parts.add("data", workflow);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.add(tenantHeaderName, tenant);
    headers.add(tokenHeaderName, token);

    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts, headers);

    ResponseEntity<JsonNode> response = this.httpService.exchange(
      String.format(requestPath, okapiLocation),
      HttpMethod.POST,
      request,
      JsonNode.class
    );

    if (response.getStatusCode() == HttpStatus.OK) {
      logger.debug("Response body: {}", response.getBody());

      try {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseBody = response.getBody();
        Workflow responseWorkflow = mapper.convertValue(responseBody, Workflow.class);
        logger.info("Workflow is actvie = {}, deploymentID = {}", responseWorkflow.isActive(), responseWorkflow.getDeploymentId());
        return workflowRepo.save(responseWorkflow);
      } catch (Exception e) {
        throw new WorkflowEngineServiceException("Unable to get updated workflow from workflow engine!");
      }
    }
    throw new WorkflowEngineServiceException(response.getStatusCodeValue());
  }

}