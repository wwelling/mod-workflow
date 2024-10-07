package org.folio.rest.workflow.kafka;

import org.folio.spring.messaging.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

  private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

  // private JmsMessagingTemplate jmsMessagingTemplate;

  // private Queue queue;

  // @Autowired
  // public EventProducer(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
  //   this.jmsMessagingTemplate = jmsMessagingTemplate;
  //   this.queue = queue;
  // }

  // public void send(Event event) throws JMSException, IOException {
  //   // CAUTION: payload not allowed in logs
  //   logger.info("Send [{}]: {}, {}", queue.getQueueName(), event.getMethod(), event.getPath());
  //   this.jmsMessagingTemplate.convertAndSend(queue, event);
  // }

  public void send(Event event) {
    // CAUTION: send not implemented
    logger.info("Send []: {}, {}", event.getMethod(), event.getPath());

  }

}
