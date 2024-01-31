package org.folio.rest.workflow.jms;

import java.io.IOException;

import org.folio.spring.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.JMSException;
import jakarta.jms.Queue;

@Service
public class EventProducer {

  private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Autowired
  private Queue queue;

  public void send(Event event) throws JMSException, IOException {
    // CAUTION: payload not allowed in logs
    logger.info("Send [{}]: {}, {}", queue.getQueueName(), event.getMethod(), event.getPath());
    this.jmsMessagingTemplate.convertAndSend(queue, event);
  }

}