package org.folio.rest.service;

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
import org.folio.rest.model.Workflow;
import org.springframework.stereotype.Service;

@Service
public class BpmnModelFactory {
  
  private static final String TARGET_NAMESPACE = "http://bpmn.io/schema/bpmn";
  
  public BpmnModelInstance makeBPMNFromWorkflow(Workflow workflow) {
    BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

    Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setTargetNamespace(TARGET_NAMESPACE);
    modelInstance.setDefinitions(definitions);

    // create the process
    Process process = createElement(modelInstance, definitions, "example-process", Process.class);
    
    process.setExecutable(true);

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

  // @formatter:off
  protected <T extends BpmnModelElementInstance> T createElement(
    BpmnModelInstance modelInstance,
    BpmnModelElementInstance parentElement,
    String id,
    Class<T> elementClass
  ) {
  // @formatter:on
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
