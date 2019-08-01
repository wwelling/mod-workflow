package org.folio.rest.controller;

import java.io.IOException;

import org.folio.rest.exception.CamundaServiceException;
import org.folio.rest.exception.WorkflowAlreadyActiveException;
import org.folio.rest.exception.WorkflowNotFoundException;
import org.folio.rest.model.Workflow;
import org.folio.rest.model.repo.WorkflowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

  @Autowired
  private WorkflowRepo workflowRepo;

  @PutMapping("/activate")
  public Workflow activateWorkflow(@RequestBody Workflow workflow)
    throws WorkflowNotFoundException, CamundaServiceException, IOException, WorkflowAlreadyActiveException {
    return workflowRepo.save(workflow);
  }

  @PutMapping("/deactivate")
  public Workflow deactivateWorkflow(@RequestBody Workflow workflow)
    throws WorkflowNotFoundException, CamundaServiceException {
    return workflowRepo.save(workflow);
  }

}
