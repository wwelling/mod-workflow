package org.folio.rest.service;

import java.io.File;
import java.io.IOException;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.folio.rest.exception.CamundaServiceException;
import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
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

  private static final Logger log = LoggerFactory.getLogger(ModCamundaService.class);

  private static final String CAMUNDA_DEPLOY_URI_TEMPLATE = "%s/camunda/deployment/create";

  private static final String CAMUNDA_UNDEPLOY_URI_TEMPLATE = "%s/camunda/deployment/%s";

  private static final String TARGET_NAMESPACE = "http://bpmn.io/schema/bpmn";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.auth.tokenHeaderName:X-Okapi-Token}")
  private String tokenHeaderName;

  @Value("${okapi.location}")
  private String okapiLocation;

  @Autowired
  private WorkflowRepo workflowRepo;

  @Autowired
  private HttpService httpService;

  public Workflow deployWorkflow(String tenant, String token, Workflow workflow)
      throws CamundaServiceException, IOException {
    BpmnModelInstance modelInstance = makeBPMNFromWorkflow(workflow);

    String modelXml = Bpmn.convertToString(modelInstance);

    HttpHeaders tenantHeader = new HttpHeaders();
    tenantHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> tenantHttpEntity = new HttpEntity<>(tenant, tenantHeader);

    HttpHeaders deploymentNameHeader = new HttpHeaders();
    deploymentNameHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentNameHttpEntity = new HttpEntity<>(workflow.getId(), deploymentNameHeader);

    HttpHeaders deploymentSourceHeader = new HttpHeaders();
    deploymentSourceHeader.setContentType(MediaType.TEXT_PLAIN);
    HttpEntity<String> deploymentSourceHttpEntity = new HttpEntity<>("process application", deploymentSourceHeader);

    HttpHeaders modelFileHeader = new HttpHeaders();
    modelFileHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    HttpEntity<ByteArrayResource> modelFileHttpEntity = new HttpEntity<>(new ByteArrayResource(modelXml.getBytes()),
        modelFileHeader);

    MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
    parts.add("tenant-id", tenantHttpEntity);
    parts.add("deployment-name", deploymentNameHttpEntity);
    parts.add("deployment-source", deploymentSourceHttpEntity);

    parts.add("data", modelFileHttpEntity);

    MultiValueMap<String, String> additionalHeaders = new LinkedMultiValueMap<>();
    additionalHeaders.add(tokenHeaderName, token);

    log.info("Tenant: {}", tenant);
    log.info("Token: {}", token);

    ResponseEntity<JsonNode> res = request(HttpMethod.POST, MediaType.MULTIPART_FORM_DATA, tenant,
        String.format(CAMUNDA_DEPLOY_URI_TEMPLATE, okapiLocation), parts, additionalHeaders);

    if (res.getStatusCode() == HttpStatus.OK) {
      workflow.setActive(true);
      return workflowRepo.save(workflow);
    }
    throw new CamundaServiceException(res.getStatusCodeValue());
  }

  public Workflow undeployWorkflow(String tenant, String token, Workflow workflow) throws CamundaServiceException {

    MultiValueMap<String, String> additionalHeaders = new LinkedMultiValueMap<>();
    additionalHeaders.add(tokenHeaderName, token);

    ResponseEntity<JsonNode> res = request(HttpMethod.DELETE, MediaType.TEXT_PLAIN, tenant,
        String.format(CAMUNDA_UNDEPLOY_URI_TEMPLATE, okapiLocation, workflow.getId()), additionalHeaders);

    if (res.getStatusCode() == HttpStatus.OK) {
      workflow.setActive(false);
      return workflowRepo.save(workflow);
    }
    throw new CamundaServiceException(res.getStatusCodeValue());
  }

  private BpmnModelInstance makeBPMNFromWorkflow(Workflow workflow) {
    BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

    Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setTargetNamespace(TARGET_NAMESPACE);
    modelInstance.setDefinitions(definitions);

    return modelInstance;
  }

  private ResponseEntity<JsonNode> request(HttpMethod method, MediaType mediaType, String tenant, String url) {
    return request(method, mediaType, tenant, url, null, null);
  }

  private ResponseEntity<JsonNode> request(HttpMethod method, MediaType mediaType, String tenant, String url,
      MultiValueMap<String, String> additionalHeaders) {
    return request(method, mediaType, tenant, url, null, additionalHeaders);
  }

  private ResponseEntity<JsonNode> request(HttpMethod method, MediaType mediaType, String tenant, String url,
      Object body) {
    return request(method, mediaType, tenant, url, body, null);
  }

  private ResponseEntity<JsonNode> request(HttpMethod method, MediaType mediaType, String tenant, String url,
      Object body, MultiValueMap<String, String> additionalHeaders) {

    log.info(url);

    HttpHeaders headers = new HttpHeaders();

    if (additionalHeaders != null) {
      headers.addAll(additionalHeaders);
    }

    headers.setContentType(mediaType);
    headers.add(tenantHeaderName, tenant);

    HttpEntity<?> request = body != null ? new HttpEntity<Object>(body, headers) : new HttpEntity<>(headers);

    if (log.isDebugEnabled()) {
      log.debug("Proxy request for {} to {}", tenant, url);
    }
    log.info("Headers {}", request.getHeaders());
    return this.httpService.exchange(url, method, request, JsonNode.class);
  }

}
