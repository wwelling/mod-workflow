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

	private static final String CAMUNDA_DEPLOY_URI_TEMPLATE = "{}/camunda/deployment/create";

	private static final String CAMUNDA_UNDEPLOY_URI_TEMPLATE = "{}/camunda/deployment/{}";

	private static final String TARGET_NAMESPACE = "http://bpmn.io/schema/bpmn";

	@Value("${tenant.headerName:X-Okapi-Tenant}")
	private String tenantHeaderName;

	@Value("${okapi.location}")
	private String okapiLocation;

	@Autowired
	private WorkflowRepo workflowRepo;

	@Autowired
	private HttpService httpService;

	public Workflow deployWorkflow(String tenant, Workflow workflow) throws CamundaServiceException, IOException {
		BpmnModelInstance modelInstance = makeBPMNFromWorkflow(workflow);

		File tempFile = File.createTempFile("workflow", ".bpmn");
		tempFile.deleteOnExit();
		
		Bpmn.writeModelToFile(tempFile, modelInstance);
		
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("tenant-id", tenant);
		map.add("deployment-name", workflow.getId());
		map.add("deployment-source", "process application");
		map.add("data", tempFile);

		ResponseEntity<JsonNode> res = request(HttpMethod.POST, MediaType.MULTIPART_FORM_DATA, tenant, String.format(CAMUNDA_DEPLOY_URI_TEMPLATE, okapiLocation), map);

		if (res.getStatusCode() == HttpStatus.OK) {
			workflow.setActive(true);
			return workflowRepo.save(workflow);
		}

		throw new CamundaServiceException(res.getStatusCodeValue());

	}

	public Workflow unDeployWorkflow(String tenant, Workflow workflow) throws CamundaServiceException {

		ResponseEntity<JsonNode> res = request(HttpMethod.DELETE, MediaType.TEXT_PLAIN, tenant, String.format(CAMUNDA_UNDEPLOY_URI_TEMPLATE, okapiLocation, workflow.getId()));

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
		definitions.setId(workflow.getId());
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
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> request = body != null ? new HttpEntity<Object>(body, headers) : new HttpEntity<>(headers);

		if (additionalHeaders != null) {
			headers.addAll(additionalHeaders);
		}

		headers.setContentType(mediaType);
		headers.add(tenantHeaderName, tenant);

		if (log.isDebugEnabled()) {
			log.debug("Proxy request for {} to {}", tenant, url);
		}
		return this.httpService.exchange(url, method, request, JsonNode.class);
	}

}
