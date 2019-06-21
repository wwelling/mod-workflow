package org.folio.rest.service;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Message;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.Script;
import org.camunda.bpm.model.bpmn.instance.ScriptTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.SubProcess;
import org.camunda.bpm.model.bpmn.instance.TerminateEventDefinition;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.folio.rest.model.Task;
import org.folio.rest.model.Workflow;
import org.springframework.stereotype.Service;

@Service
public class BpmnModelFactory {

  private static final String TARGET_NAMESPACE = "http://bpmn.io/schema/bpmn";


  public BpmnModelInstance makeBPMNFromWorkflow(Workflow workflow) {

    BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

    // Setup Definitions
    Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setTargetNamespace(TARGET_NAMESPACE);
    modelInstance.setDefinitions(definitions);
    
    // Setup Process
    Process process = createElement(modelInstance, definitions, workflow.getName().replaceAll(" ", "_").toLowerCase(),
    Process.class);
    process.setExecutable(true);
    process.setName(workflow.getName());

    // Setup Nodes
    StartEvent processStartEvent = createElement(modelInstance, process, "_1", StartEvent.class);
    processStartEvent.setName("StartProcess");

    ServiceTask serviceTask = createElement(modelInstance, process, "_2", ServiceTask.class);
    serviceTask.setName("ServiceTask");
    serviceTask.setCamundaDelegateExpression("${loggingDelagate}");

    EndEvent endEvent = createElement(modelInstance, process, "_3", EndEvent.class);
    endEvent.setName("EndProcess");
    createElement(modelInstance, endEvent, null, TerminateEventDefinition.class);

    // Setup Connections
    SequenceFlow oneToTwoSequenceFlow = createElement(modelInstance, process, "_1-_2", SequenceFlow.class);
    oneToTwoSequenceFlow.setSource(processStartEvent);
    oneToTwoSequenceFlow.setTarget(serviceTask);
    SequenceFlow twoToThreeSequenceFlow = createElement(modelInstance, process, "_2-_3", SequenceFlow.class);
    twoToThreeSequenceFlow.setSource(serviceTask);
    twoToThreeSequenceFlow.setTarget(endEvent);

    Message processStartMessage = createElement(modelInstance, definitions, "process-start-message", Message.class);
    processStartMessage.setName(workflow.getStartTrigger().getPathPattern());

    MessageEventDefinition processStartEventMessageDefinition = createElement(modelInstance, processStartEvent,
        "process-start-event-message-definition", MessageEventDefinition.class);
    processStartEventMessageDefinition.setMessage(processStartMessage);

    // Stub Empty Diagram
    BpmnDiagram diagramElement = createElement(modelInstance, definitions,
      String.format("%s-diagram", workflow.getName().replaceAll(" ", "_").toLowerCase()), BpmnDiagram.class);

    BpmnPlane plane = createElement(modelInstance, diagramElement,
      String.format("%s-plane", workflow.getName().replaceAll(" ", "_").toLowerCase()), BpmnPlane.class);
    plane.setBpmnElement(process);

    return modelInstance;
  }

  // public BpmnModelInstance makeBPMNFromWorkflow(Workflow workflow) {

  //   BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

  //   Definitions definitions = modelInstance.newInstance(Definitions.class);
  //   definitions.setTargetNamespace(TARGET_NAMESPACE);
  //   modelInstance.setDefinitions(definitions);

  //   // create the process
    // Process process = createElement(modelInstance, definitions, workflow.getName().replaceAll(" ", "_").toLowerCase(),
    //     Process.class);
    // process.setExecutable(true);
    // process.setName(workflow.getName());

    // Message processStartMessage = createElement(modelInstance, definitions, "process-start-message", Message.class);
    // processStartMessage.setName(workflow.getStartTrigger().getPathPattern());

    // StartEvent processStartEvent = createElement(modelInstance, process, "process-start", StartEvent.class);
    // MessageEventDefinition processStartEventMessageDefinition = createElement(modelInstance, processStartEvent,
    //     "process-start-event-message-definition", MessageEventDefinition.class);
    // processStartEventMessageDefinition.setMessage(processStartMessage);

  //   EndEvent processEndEvent = createElement(modelInstance, process, "process-end", EndEvent.class);
    
  //   SubProcess subProcess = createElement(modelInstance, process, "sub-process", SubProcess.class);

  //   //Initial Sequence Flow
  //   SequenceFlow sequenceFlowZero = createElement(modelInstance, process, "sequence-flow-first", SequenceFlow.class);
  //   sequenceFlowZero.setSource(processStartEvent);
  //   sequenceFlowZero.setTarget(subProcess);

  //   SequenceFlow sequenceFlowLast = createElement(modelInstance, process, "sequence-flow-last", SequenceFlow.class);
  //   sequenceFlowLast.setSource(subProcess);
  //   sequenceFlowLast.setTarget(processEndEvent);

  //   // Attach Tasks
    // AtomicInteger taskIndex = new AtomicInteger();
    // List<ServiceTask> serviceTasks = workflow.getTasks().stream()
    //     .map(t -> enhanchUserTask(
    //         createElement(modelInstance, subProcess, String.format("task-%s", taskIndex.incrementAndGet()), ServiceTask.class),
    //         t))
    //     .collect(Collectors.toList());


  //   StartEvent subProcessStartEvent = createElement(modelInstance, subProcess, "sub-process-start", StartEvent.class);

  //   // Outgoing subProcessStartEventOutgoing = createElement(modelInstance, subProcessStartEvent, null, Outgoing.class);
  //   // subProcessStartEventOutgoing.setTextContent(serviceTasks.get(0).getId());

  //   EndEvent subProcessEndEvent = createElement(modelInstance, subProcess, "sub-process-end", EndEvent.class);

  //   // Incoming subProcessEndEventIncoming = createElement(modelInstance, subProcessEndEvent, null, Incoming.class);
  //   // subProcessEndEventIncoming.setTextContent(serviceTasks.get(serviceTasks.size()-1).getId());
    
  //   // Build Sequence Flow
  //   AtomicInteger sequenceFlowIndex = new AtomicInteger();
  //   serviceTasks.forEach(st->{
  //     int sequenceFlowCount = sequenceFlowIndex.getAndIncrement();
  //     SequenceFlow sf = createElement(modelInstance, subProcess, String.format("sequence-flow-%s", sequenceFlowIndex.get()), 
  //         SequenceFlow.class);
  //     sf.setSource(serviceTasks.get(sequenceFlowCount));

  //     if(sequenceFlowCount == 0) {
  //       sf.setTarget(serviceTasks.get(sequenceFlowIndex.get()));
  //       Outgoing subProcessStartEventOutgoing = createElement(modelInstance, subProcessStartEvent, null, Outgoing.class);
  //       subProcessStartEventOutgoing.setTextContent(sf.getId());
  //     } else if(sequenceFlowCount == serviceTasks.size()-1) {
  //       sf.setTarget(subProcessEndEvent);
  //       Incoming subProcessEndEventIncoming = createElement(modelInstance, subProcessEndEvent, null, Incoming.class);
  //       subProcessEndEventIncoming.setTextContent(sf.getId());
  //     } else {
  //       sf.setTarget(serviceTasks.get(sequenceFlowIndex.get()));
  //     }

  //   });
    

  //   Outgoing processStartEventOutgoing = createElement(modelInstance, processStartEvent, null, Outgoing.class);
  //   processStartEventOutgoing.setTextContent(sequenceFlowZero.getId());

  //   Incoming subProcessIncoming = createElement(modelInstance, subProcess, null, Incoming.class);
  //   subProcessIncoming.setTextContent(sequenceFlowZero.getId());

  //   Outgoing subProcessOutgoing = createElement(modelInstance, subProcess, null, Outgoing.class);
  //   subProcessOutgoing.setTextContent(sequenceFlowLast.getId());

    // //Build Empty Diagram
    // BpmnDiagram diagramElement = createElement(modelInstance, definitions,
    //     String.format("%s-diagram", workflow.getName().replaceAll(" ", "_").toLowerCase()), BpmnDiagram.class);

    // BpmnPlane plane = createElement(modelInstance, diagramElement,
    // String.format("%s-plane", workflow.getName().replaceAll(" ", "_").toLowerCase()), BpmnPlane.class);
    // plane.setBpmnElement(process);

  //   return modelInstance;
  // }

  // @formatter:off
  protected <T extends BpmnModelElementInstance> T createElement(
    BpmnModelInstance modelInstance,
    BpmnModelElementInstance parentElement,
    String id,
    Class<T> elementClass
  ) {
  // @formatter:on
    T element = modelInstance.newInstance(elementClass);
    if(id != null) element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }

  public Message createMessage(BpmnModelInstance modelInstance, Process process, String id, String textContent) {
    Message message = createElement(modelInstance, process, id, Message.class);
    message.setTextContent(textContent);
    return message;
  }


  // <bpmn:boundaryEvent id="BoundaryEvent_1tu52i2" attachedToRef="SubProcess_0vakfj9">
  //     <bpmn:outgoing>SequenceFlow_1ngs0ny</bpmn:outgoing>
  //     <bpmn:messageEventDefinition messageRef="Message_1460phb" />
  //   </bpmn:boundaryEvent>

  public BoundaryEvent createBoundaryEvent(BpmnModelInstance modelInstance, Process process, String id, String textContent) {
    BoundaryEvent boundaryEvent = createElement(modelInstance, process, id, BoundaryEvent.class);
    return boundaryEvent;
  }

  public SequenceFlow createSequenceFlow(BpmnModelInstance modelInstance, SubProcess subProcess, FlowNode from, FlowNode to) {
    String id = from.getId() + "-" + to.getId();
    SequenceFlow sequenceFlow = createElement(modelInstance, subProcess, id, SequenceFlow.class);
    subProcess.addChildElement(sequenceFlow);
    sequenceFlow.setSource(from);
    from.getOutgoing().add(sequenceFlow);
    sequenceFlow.setTarget(to);
    to.getIncoming().add(sequenceFlow);
    return sequenceFlow;
  }

  private ServiceTask enhanchServiceTask(ServiceTask serviceTask, Task task) {
    serviceTask.setName(task.getName());
    serviceTask.setCamundaDelegateExpression("${loggingDelagate}");
    return serviceTask;
  }

}
