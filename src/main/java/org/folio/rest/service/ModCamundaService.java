package org.folio.rest.service;

import java.io.File;
import java.io.IOException;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.folio.rest.exception.CamundaServiceException;
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

  public Workflow deployWorkflow(String tenant, String token, Workflow workflow) throws CamundaServiceException, IOException {
    BpmnModelInstance modelInstance = makeBPMNFromWorkflow(workflow);

    Bpmn.validateModel(modelInstance);

    File modelXmlFile = File.createTempFile("workflow-model", ".bpmn");
    modelXmlFile.deleteOnExit();

    Bpmn.writeModelToFile(modelXmlFile, modelInstance);

    FileSystemResource modelXml = new FileSystemResource(modelXmlFile);

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
    HttpEntity<FileSystemResource> modelFileHttpEntity = new HttpEntity<>(modelXml, modelFileHeader);

    MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
    parts.add("tenant-id", tenantHttpEntity);
    parts.add("deployment-name", deploymentNameHttpEntity);
    parts.add("deployment-source", deploymentSourceHttpEntity);

    parts.add("file", modelFileHttpEntity);

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.add(tenantHeaderName, tenant);
    headers.add(tokenHeaderName, token);

    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts, headers);

    ResponseEntity<JsonNode> response = this.httpService.exchange(String.format(CAMUNDA_DEPLOY_URI_TEMPLATE, okapiLocation), HttpMethod.POST, request, JsonNode.class);

    if (response.getStatusCode().equals(HttpStatus.OK)) {
      workflow.setDeploymentId(response.getBody().get("id").asText());
      workflow.setActive(true);
      return workflowRepo.save(workflow);
    }
    throw new CamundaServiceException(response.getStatusCodeValue());
  }

  public Workflow undeployWorkflow(String tenant, String token, Workflow workflow) throws CamundaServiceException {

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.add(tenantHeaderName, tenant);
    headers.add(tokenHeaderName, token);

    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity<JsonNode> response = this.httpService.exchange(String.format(CAMUNDA_UNDEPLOY_URI_TEMPLATE, okapiLocation, workflow.getDeploymentId()), HttpMethod.DELETE, request, JsonNode.class);

    if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
      workflow.setActive(false);
      workflow.setDeploymentId(null);
      return workflowRepo.save(workflow);
    }
    throw new CamundaServiceException(response.getStatusCodeValue());
  }

  private BpmnModelInstance makeBPMNFromWorkflow(Workflow workflow) {
    BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

    Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setTargetNamespace(TARGET_NAMESPACE);
    modelInstance.setDefinitions(definitions);

    // create the process
    Process process = createElement(modelInstance, definitions, "example-process", Process.class);

    // create start event, user task and end event
    StartEvent startEvent = createElement(modelInstance, process, "start", StartEvent.class);
    UserTask userTask = createElement(modelInstance, process, "task", UserTask.class);
    userTask.setName("User Task");
    EndEvent endEvent = createElement(modelInstance, process, "end", EndEvent.class);

    // create the connections between the elements
    createSequenceFlow(modelInstance, process, startEvent, userTask);
    createSequenceFlow(modelInstance, process, userTask, endEvent);

    return modelInstance;
  }

  protected <T extends BpmnModelElementInstance> T createElement(BpmnModelInstance modelInstance, BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = modelInstance.newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }

  public SequenceFlow createSequenceFlow(BpmnModelInstance modelInstance, Process process, FlowNode from, FlowNode to) {
    String identifier = from.getId() + "-" + to.getId();
    SequenceFlow sequenceFlow = createElement(modelInstance, process, identifier, SequenceFlow.class);
    process.addChildElement(sequenceFlow);
    sequenceFlow.setSource(from);
    from.getOutgoing().add(sequenceFlow);
    sequenceFlow.setTarget(to);
    to.getIncoming().add(sequenceFlow);
    return sequenceFlow;
  }

}
