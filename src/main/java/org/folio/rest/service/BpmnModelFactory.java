package org.folio.rest.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

  private static final String END_EVENT_ID = "ee_0";
  private static final String START_EVENT_ID = "se_0";
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

    StartEvent processStartEvent = createElement(modelInstance, process, START_EVENT_ID, StartEvent.class);
    processStartEvent.setName("StartProcess");

    AtomicInteger taskIndex = new AtomicInteger();
    List<ServiceTask> serviceTasks = workflow.getTasks().stream().map(task -> {
      int index = taskIndex.getAndIncrement();
      ServiceTask serviceTask = createElement(modelInstance, process, String.format("t_%s", index), ServiceTask.class);
      String delegate = index == 0 ? "${testStreamDelegate}" : index < workflow.getTasks().size() - 1 ? "${testProcessDelegate}" : "${testAccumulatorDelegate}";
      return enhanceServiceTask(serviceTask, task, delegate);
    }).collect(Collectors.toList());

    EndEvent processEndEvent = createElement(modelInstance, process, END_EVENT_ID, EndEvent.class);
    processEndEvent.setName("EndProcess");
    createElement(modelInstance, processEndEvent, null, TerminateEventDefinition.class);
    

    // ServiceTask serviceTask = createElement(modelInstance, process, "_2", ServiceTask.class);
    // serviceTask.setName("ServiceTask");
    // serviceTask.setCamundaDelegateExpression("${loggingDelagate}");

    

    SequenceFlow firstSf = createElement(modelInstance, process, String.format("%s-%s", START_EVENT_ID, "t_1"), SequenceFlow.class);
    firstSf.setSource(processStartEvent);
    firstSf.setTarget(serviceTasks.get(0));

    // Setup Connections
    AtomicInteger sfIndex = new AtomicInteger();
    serviceTasks.forEach(st->{
      int currentIndex = sfIndex.getAndIncrement();
      if(currentIndex != serviceTasks.size()-1) {
        SequenceFlow currentSf = createElement(modelInstance, process, String.format("t_%s-t_%s", currentIndex, currentIndex+1), SequenceFlow.class);
        currentSf.setTarget(serviceTasks.get(currentIndex+1));
        currentSf.setSource(st);
      }
    });

    SequenceFlow lastSf = createElement(modelInstance, process, String.format("t_%s-%s", serviceTasks.size(), END_EVENT_ID), SequenceFlow.class);
    lastSf.setSource(serviceTasks.get(serviceTasks.size()-1));
    lastSf.setTarget(processEndEvent);

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

  private ServiceTask enhanceServiceTask(ServiceTask serviceTask, Task task, String delegate) {
    serviceTask.setName(task.getName());
    serviceTask.setCamundaDelegateExpression(delegate);
    return serviceTask;
  }

}
