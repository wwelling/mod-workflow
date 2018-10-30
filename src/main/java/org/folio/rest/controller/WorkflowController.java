package org.folio.rest.controller;

import java.util.Optional;

import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.folio.rest.tenant.annotation.TenantHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

  @Autowired
	private WorkflowRepo workflowRepo;

	@GetMapping("/{id}")
	public Workflow deployWorkflow(@TenantHeader String tenant, @Param("id") String workflowId) {
		
		Optional<Workflow> workflowToUpdate = workflowRepo.findById(workflowId);
		Workflow updatedWorkflow = null;

		if (workflowToUpdate.isPresent()) {
			//attempt to deploy
			updatedWorkflow = workflowToUpdate.get();
			updatedWorkflow.setDeployed(true);
			updatedWorkflow = workflowRepo.save(updatedWorkflow);
		} else {
			// throw error cannot find workflow
		}
		
		return updatedWorkflow;
  }

}
