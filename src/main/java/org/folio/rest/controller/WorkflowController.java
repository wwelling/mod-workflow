package org.folio.rest.controller;

import org.folio.rest.annotation.TokenHeader;
import org.folio.rest.exception.WorkflowEngineServiceException;
import org.folio.rest.model.Workflow;
import org.folio.rest.service.WorkflowEngineService;
import org.folio.rest.tenant.annotation.TenantHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

  @Autowired
  private WorkflowEngineService workflowEngineService;

  @PutMapping("/activate")
  public Workflow activateWorkflow(
    @RequestBody Workflow workflow,
    @TenantHeader String tenant,
    @TokenHeader String token
  ) throws WorkflowEngineServiceException {
    return workflowEngineService.activate(workflow, tenant, token);
  }

  @PutMapping("/deactivate")
  public Workflow deactivateWorkflow(
    @RequestBody Workflow workflow,
    @TenantHeader String tenant,
    @TokenHeader String token
  ) throws WorkflowEngineServiceException {
    return workflowEngineService.deactivate(workflow, tenant, token);
  }

}
