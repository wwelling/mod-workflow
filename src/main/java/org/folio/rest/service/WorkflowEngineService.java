package org.folio.rest.service;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;

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
      String.format("%s/", okapiLocation),
      HttpMethod.POST,
      request,
      JsonNode.class
    );

    if (response.getStatusCode() == HttpStatus.OK) {
      logger.debug("Response body: {}", response.getBody());
      workflow.setDeploymentId(response.getBody().get("id").asText());

      try {
        JsonNode deployedProcessDefinitionsNode = response.getBody().get("deployedProcessDefinitions");
        Iterator<String> dpdni = deployedProcessDefinitionsNode.fieldNames();
        while (dpdni.hasNext()) {
          workflow.addProcessDefinitionId(dpdni.next());
        }
        workflow.setActive(true);
        logger.info("Deployed workflow {} with deployment id {}", workflow.getName(), workflow.getDeploymentId());
        return workflowRepo.save(workflow);
      } catch (Exception e) {
        throw new WorkflowEngineServiceException("Unable to get deployed process definition id!");
      }
    }
    throw new WorkflowEngineServiceException(response.getStatusCodeValue());
  }

}