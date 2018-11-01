package org.folio.rest.controller;

import java.io.IOException;
import java.util.Optional;

import org.folio.rest.exception.CamundaServiceException;
import org.folio.rest.exception.WorkflowNotFoundException;
import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.folio.rest.service.ModCamundaService;
import org.folio.rest.tenant.annotation.TenantHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

  @Autowired
	private WorkflowRepo workflowRepo;

	@Autowired
	private ModCamundaService modCamundaService;

	@PutMapping("/{id}/activate")
	public Workflow activateWorkflow(@TenantHeader String tenant, @PathVariable String id)
			throws WorkflowNotFoundException, CamundaServiceException, IOException {

		Optional<Workflow> workflow = workflowRepo.findById(id);

		if (workflow.isPresent()) {
			return modCamundaService.deployWorkflow(tenant, workflow.get());
		}

		throw new WorkflowNotFoundException(id);

	}

	@PutMapping("/{id}/deactivate")
	public Workflow deactivateWorkflow(@TenantHeader String tenant, @PathVariable String id)
			throws WorkflowNotFoundException, CamundaServiceException {
		
		Optional<Workflow> workflow = workflowRepo.findById(id);

		if (workflow.isPresent()) {
			return modCamundaService.unDeployWorkflow(tenant, workflow.get());
		}

		throw new WorkflowNotFoundException(id);

	}

}
