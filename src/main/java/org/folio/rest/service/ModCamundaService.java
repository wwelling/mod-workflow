package org.folio.rest.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.folio.rest.exception.CamundaServiceException;
import org.folio.rest.exception.WorkflowAlreadyActiveException;
import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ModCamundaService {

  private static final Logger logger = LoggerFactory.getLogger(ModCamundaService.class);

  private static final String CAMUNDA_DEPLOY_URI_TEMPLATE = "%s/camunda/deployment/create";

  private static final String CAMUNDA_UNDEPLOY_URI_TEMPLATE = "%s/camunda/deployment/%s";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.auth.tokenHeaderName:X-Okapi-Token}")
  private String tokenHeaderName;

  @Value("${okapi.location}")
  private String okapiLocation;

  @Autowired
  private WorkflowRepo workflowRepo;

  @Autowired
  private BpmnModelFactory bpmnModelFactory;

  @Autowired
  private HttpService httpService;

  // @formatter:off
  public Workflow deployWorkflow(
    String tenant,
    String token,
    Workflow workflow
  ) throws CamundaServiceException, IOException, WorkflowAlreadyActiveException {
  // @formatter:on

    if (workflow.isActive()) {
      throw new WorkflowAlreadyActiveException(workflow.getId());
    }

    BpmnModelInstance modelInstance = bpmnModelFactory.makeBPMNFromWorkflow(workflow);

    Bpmn.validateModel(modelInstance);

    File modelXmlFile = File.createTempFile(workflow.getName(), ".bpmn");
    modelXmlFile.deleteOnExit();

    Bpmn.writeModelToFile(modelXmlFile, modelInstance);

    FileSystemResource modelXml = new FileSystemResource(modelXmlFile);

    HttpHeaders tenantHeader = new HttpHeaders();
    tenantHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> tenantHttpEntity = new HttpEntity<>(tenant, tenantHeader);

    HttpHeaders deploymentNameHeader = new HttpHeaders();
    deploymentNameHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentNameHttpEntity = new HttpEntity<>(workflow.getName(), deploymentNameHeader);

    HttpHeaders deploymentSourceHeader = new HttpHeaders();
    deploymentSourceHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentSourceHttpEntity = new HttpEntity<>("process application", deploymentSourceHeader);

    HttpHeaders modelFileHeader = new HttpHeaders();
    modelFileHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    HttpEntity<FileSystemResource> modelFileHttpEntity = new HttpEntity<>(modelXml, modelFileHeader);

    MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
    parts.add("tenant-id", tenantHttpEntity);
    parts.add("deployment-name", deploymentNameHttpEntity);
    parts.add("deployment-source", deploymentSourceHttpEntity);

    parts.add("data", modelFileHttpEntity);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.add(tenantHeaderName, tenant);
    headers.add(tokenHeaderName, token);

    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts, headers);

    // @formatter:off
    ResponseEntity<JsonNode> response = this.httpService.exchange(
      String.format(CAMUNDA_DEPLOY_URI_TEMPLATE, okapiLocation),
      HttpMethod.POST,
      request,
      JsonNode.class
    );
    // @formatter:on

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
        throw new CamundaServiceException("Unable to get deployed process definition id!");
      }
    }
    throw new CamundaServiceException(response.getStatusCodeValue());
  }

  public Workflow undeployWorkflow(String tenant, String token, Workflow workflow) throws CamundaServiceException {

    HttpHeaders headers = new HttpHeaders();
    headers.add(tenantHeaderName, tenant);
    headers.add(tokenHeaderName, token);

    HttpEntity<String> request = new HttpEntity<>(headers);

    // @formatter:off
    ResponseEntity<JsonNode> response = this.httpService.exchange(
      String.format(CAMUNDA_UNDEPLOY_URI_TEMPLATE,okapiLocation, workflow.getDeploymentId()),
      HttpMethod.DELETE,
      request,
      JsonNode.class
    );
    // @formatter:on

    if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
      workflow.setActive(false);
      workflow.setDeploymentId(null);
      workflow.clearProcessDefinitionIds();
      return workflowRepo.save(workflow);
    }
    throw new CamundaServiceException(response.getStatusCodeValue());
  }

}
